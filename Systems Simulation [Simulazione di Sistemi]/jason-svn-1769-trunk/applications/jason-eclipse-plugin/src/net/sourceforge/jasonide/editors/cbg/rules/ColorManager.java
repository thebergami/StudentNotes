package net.sourceforge.jasonide.editors.cbg.rules;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sourceforge.jasonide.editors.cbg.ColoringPartitionScanner;
import net.sourceforge.jasonide.editors.cbg.prefs.ColorsPreferencePage;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {
    public static final RGB DEFAULT_STRING_COLOR = new RGB(0, 0, 0);
    public static final RGB DEFAULT_KEYWORD1_COLOR = new RGB(128, 128, 255);
    public static final RGB DEFAULT_KEYWORD2_COLOR = new RGB(255, 0, 128);
    public static final RGB DEFAULT_KEYWORD3_COLOR = new RGB(255, 128, 0);  
    public static final RGB DEFAULT_COMMENT1_COLOR = new RGB(193, 0, 0);
    public static final RGB DEFAULT_COMMENT2_COLOR = new RGB(255, 128, 0);
    public static final RGB DEFAULT_LITERAL1_COLOR = new RGB(255, 89, 172);
    public static final RGB DEFAULT_LITERAL2_COLOR = new RGB(180, 116, 179);
    public static final RGB DEFAULT_LABEL_COLOR = new RGB(255, 128, 0);
    public static final RGB DEFAULT_FUNCTION_COLOR = new RGB(255, 0, 255);  
    public static final RGB DEFAULT_MARKUP_COLOR = new RGB(0, 0, 255);
    public static final RGB DEFAULT_OPERATOR_COLOR = new RGB(128, 0, 255);
    public static final RGB DEFAULT_DIGIT_COLOR = new RGB(255, 0, 0);
    public static final RGB DEFAULT_INVALID_COLOR = new RGB(255, 255, 128);

    private Map colorMap;
    private IPreferenceStore store;
    private Map typeToColorMap;

    public ColorManager(IPreferenceStore store) {
        colorMap = new HashMap();
        this.store = store;
        initTypeToColorMap();
    }

    public Color getColor(String colorName) {
        RGB prefColor = PreferenceConverter.getColor(store, colorName);
        Color color = null;
        if (colorMap.containsKey(colorName)
            && (color = (Color) colorMap.get(colorName)).getRGB().equals(prefColor)) {
            color = (Color) colorMap.get(colorName);
        } else {
            color = new Color(Display.getDefault(), prefColor);
            colorMap.put(colorName, color);
        }
        return color;
    }

    public void dispose() {
        Collection colors = colorMap.values();
        for (Iterator iter = colors.iterator(); iter.hasNext();) {
            Color color = (Color) iter.next();
            colorMap.remove(color);
            color.dispose();
        }
        colorMap = null;
    }

    /** 
     * Converts the ColoringPartitionScanner's type
     * name to the appropriate preference store color
     * name.
     * @param type
     * @return String
     */
    public String colorForType(String type) {
        String kind = (String) typeToColorMap.get(type);
        if(kind == null) {
            "".trim();
        }
        return kind; 
    }
    
    protected void initTypeToColorMap() {
        typeToColorMap = new HashMap();
        typeToColorMap.put(ColoringPartitionScanner.COMMENT1, ColorsPreferencePage.COMMENT1_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.COMMENT2, ColorsPreferencePage.COMMENT2_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.LITERAL1, ColorsPreferencePage.LITERAL1_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.LITERAL2, ColorsPreferencePage.LITERAL2_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.LABEL, ColorsPreferencePage.LABEL_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.KEYWORD1, ColorsPreferencePage.KEYWORD1_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.KEYWORD2, ColorsPreferencePage.KEYWORD2_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.KEYWORD3, ColorsPreferencePage.KEYWORD3_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.KEYWORD4, ColorsPreferencePage.KEYWORD4_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.FUNCTION, ColorsPreferencePage.FUNCTION_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.MARKUP, ColorsPreferencePage.MARKUP_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.OPERATOR, ColorsPreferencePage.OPERATOR_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.DIGIT, ColorsPreferencePage.DIGIT_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.INVALID, ColorsPreferencePage.INVALID_COLOR);
        typeToColorMap.put(ColoringPartitionScanner.NULL, ColorsPreferencePage.NULL_COLOR);
    }
    
    public Color getColorForType(String type) {
        String colorName = colorForType(type);
        if(colorName == null) colorName = ColorsPreferencePage.NULL_COLOR;
        return getColor(colorName);
    }

    public static void initDefaultColors(IPreferenceStore store) {
        PreferenceConverter.setDefault(store, ColorsPreferencePage.NULL_COLOR, DEFAULT_STRING_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.COMMENT1_COLOR, DEFAULT_COMMENT1_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.COMMENT2_COLOR, DEFAULT_COMMENT2_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.LITERAL1_COLOR, DEFAULT_LITERAL1_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.LITERAL2_COLOR, DEFAULT_LITERAL2_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.LABEL_COLOR, DEFAULT_LABEL_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.KEYWORD1_COLOR, DEFAULT_KEYWORD1_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.KEYWORD2_COLOR, DEFAULT_KEYWORD2_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.KEYWORD3_COLOR, DEFAULT_KEYWORD3_COLOR);
        //PreferenceConverter.setDefault(store, ColorsPreferencePage.KEYWORD4_COLOR, DEFAULT_KEYWORD4_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.FUNCTION_COLOR, DEFAULT_FUNCTION_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.MARKUP_COLOR, DEFAULT_MARKUP_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.OPERATOR_COLOR, DEFAULT_OPERATOR_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.DIGIT_COLOR, DEFAULT_DIGIT_COLOR);
        PreferenceConverter.setDefault(store, ColorsPreferencePage.INVALID_COLOR, DEFAULT_INVALID_COLOR);
        
        String bold = ColorsPreferencePage.BOLD_SUFFIX;
        store.setDefault(ColorsPreferencePage.COMMENT1_COLOR + bold, false);
        store.setDefault(ColorsPreferencePage.COMMENT2_COLOR + bold, false);        
        store.setDefault(ColorsPreferencePage.DIGIT_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.FUNCTION_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.INVALID_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.KEYWORD1_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.KEYWORD2_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.KEYWORD3_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.KEYWORD4_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.LABEL_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.LITERAL1_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.LITERAL2_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.MARKUP_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.OPERATOR_COLOR + bold,  false);
        store.setDefault(ColorsPreferencePage.NULL_COLOR + bold,  false);
    }

    public int getStyleFor(String colorName) {
        String boldSuffix = ColorsPreferencePage.BOLD_SUFFIX;
        if(colorName == null) colorName = ColorsPreferencePage.NULL_COLOR;
        String boldName = colorName + boldSuffix;
        boolean isBold = store.getBoolean(boldName);
        return isBold ? SWT.BOLD : SWT.NORMAL;
    }
    public int getStyleForType(String type) {
        return getStyleFor(colorForType(type));
    }
}
