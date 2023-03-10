/*
* generated by Xtext
*/
package jasonide.xtext.asl.ui.labeling;

import jasonide.xtext.asl.asl.Agent;

import jasonide.xtext.asl.asl.Arithm_expr;
import jasonide.xtext.asl.asl.Arithm_expr_factor;
import jasonide.xtext.asl.asl.Arithm_expr_simple;
import jasonide.xtext.asl.asl.Arithm_expr_trm;
import jasonide.xtext.asl.asl.Belief;
import jasonide.xtext.asl.asl.Function;
import jasonide.xtext.asl.asl.List;
import jasonide.xtext.asl.asl.Literal;
import jasonide.xtext.asl.asl.Log_expr;
import jasonide.xtext.asl.asl.Log_expr_factor;
import jasonide.xtext.asl.asl.Log_expr_trm;
import jasonide.xtext.asl.asl.Plan;
import jasonide.xtext.asl.asl.Pred;
import jasonide.xtext.asl.asl.Number;
import jasonide.xtext.asl.asl.Rel_expr;
import jasonide.xtext.asl.asl.Term;
import jasonide.xtext.asl.asl.Term_in_list;
import jasonide.xtext.asl.asl.Terms;
import jasonide.xtext.asl.asl.Var;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.ui.label.DefaultEObjectLabelProvider;

import com.google.inject.Inject;

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
public class AslLabelProvider extends DefaultEObjectLabelProvider {
	@Inject
	public AslLabelProvider(AdapterFactoryLabelProvider delegate) {
		super(delegate);
	}
	
	public String text(Object element) {
		
		if (element instanceof Agent) {
			return "Plans";
		} else if (element instanceof Plan) {
			if (((Plan) element).getTrigger().getLiteral() != null) {
				return ((Plan) element).getTrigger().getTrigger() + literalToString(((Plan) element).getTrigger().getLiteral()) + 
				(((Plan) element).getTk_label_at() != null ? " (@" + ((Plan) element).getPred().getAtom() + ")" : "" );
				/*return ((Plan) element).getTrigger().getTrigger() + (((Plan) element).getTrigger().getLiteral().getTk_neg() != null ? ((Plan) element).getTrigger().getLiteral().getTk_neg() : "") + ((Plan) element).getTrigger().getLiteral().getPred().getAtom() + 
						(((Plan) element).getTk_label_at() != null ? " (@" + ((Plan) element).getPred().getAtom() + ")" : "" );*/
			} else {
				if (((Plan) element).getTrigger().getVar().getVar() != null) {
					return ((Plan) element).getTrigger().getTrigger()  + ((Plan) element).getTrigger().getVar().getVar() + 
							(((Plan) element).getTk_label_at() != null ? " (@" + ((Plan) element).getPred().getAtom() + ")" : "" );
				} else if (((Plan) element).getTrigger().getVar().getUnnamedvar() != null) {
					return ((Plan) element).getTrigger().getTrigger()  + ((Plan) element).getTrigger().getVar().getUnnamedvar() + 
							(((Plan) element).getTk_label_at() != null ? " (@" + ((Plan) element).getPred().getAtom() + ")" : "" );
				} else {
					return ((Plan) element).getTrigger().getTrigger()  + ((Plan) element).getTrigger().getVar().getList() + 
							(((Plan) element).getTk_label_at() != null ? " (@" + ((Plan) element).getPred().getAtom() + ")" : "" );					
				}
			}
		} else if (element instanceof Belief) {
			String res = "";
			res += literalToString(((Belief) element).getLiteral());
			
			//TreeIterator<INode> it = root.getAsTreeIterable().iterator();
			
			
			return res;
		}
		return null;
	}
	
	private String literalToString(Literal l) {
		String res = "";
		if (l.getTk_neg() != null) {
			res += "~";
		}
		if (l.getPred() != null) {
			res += predToString(l.getPred());
		} else if (l.getTk_false() != null) {
			res += l.getTk_false();
		} else if (l.getTk_true() != null) {
			res += l.getTk_true();
		}
		return res;
	}
	
	private String predToString(Pred p) {
		String res = "";
		
		if (p.getAtom() != null) {
			res += p.getAtom();
		} else if (p.getTk_begin() != null) {
			res += p.getTk_begin();
		} else if (p.getTk_end() != null) {
			res += p.getTk_end();
		}
		if (p.getTerms() != null) {
			res += termsToString(p.getTerms());
		} 
		if (p.getPlan_term() != null) {
			if (p.getPlan_term().size() > 0) {
				//TODO Plan_term
				res += "{..}";
			}
		}
		if (p.getList() != null) {
			res += listToString(p.getList());
		}
		
		return res;
	}
	
	private String termsToString(Terms t) {
		String res = "(";
		
		for (Term term : t.getTerm()) {
			if (term.getList() != null) {
				if (res.equals("(")) {
					res += listToString(term.getList());
				} else {
					res += "," + listToString(term.getList());
				}
			} else if (term.getLog_expr() != null) {
				if (res.equals("(")) {
					res += log_exprToString(term.getLog_expr());
				} else {
					res += "," + log_exprToString(term.getLog_expr());
				}				
			} else if (term.getPlan_term() != null) {
				res += "{..}";
				//TODO Plan_term
			}
		}
		
		res += ")";
		return res;
	}
	
	private String listToString(List l) {
		String res = "[";
		
		for (Term_in_list term : l.getTerm_in_list()) {
			if (res.equals("[")) {
				res += term_in_listToString(term); 
			} else {
				res += "," + term_in_listToString(term);
			}
		}
		
		if (l.getVar() != null) {
			res += "|" + l.getVar();
		} else if (l.getUnnamedvar() != null) {
			res += "|" + l.getUnnamedvar();
		} else if (l.getList() != null) {
			res += "|" + listToString(l.getList());
		}
		
		res += "]";
		return res;
	}
	
	private String term_in_listToString(Term_in_list t) {
		String res = "";
		
		if (t.getArithm_expr() != null) {
			res += arithm_exprToString(t.getArithm_expr());
		} else if (t.getString() != null) {
			res += t.getString().getString();
		} else if (t.getList() != null) {
			res += listToString(t.getList());
		} else if (t.getPlan_term() != null) {
			res += "{..}";
			//TODO Plan_term
		}
		
		
		
		return res;
	}
	
	private String log_exprToString(Log_expr l) {
		String res = "";
		if (l.getLog_expr_trm() != null) {
			res += log_expr_trmToString(l.getLog_expr_trm());
		}
		if (l.getLog_expr() != null) {
			res += "|" + log_exprToString(l.getLog_expr());
		}
		
		return res;
	}
	
	private String log_expr_trmToString(Log_expr_trm l) {
		String res = "";
		
		if (l.getLog_expr_factor() != null) {
			res += log_expr_factorToString(l.getLog_expr_factor());
		}
		if (l.getLog_expr_trm() != null) {
			res += "|" + log_expr_trmToString(l.getLog_expr_trm());
		}
		
		return res;
	}
	
	private String log_expr_factorToString(Log_expr_factor l) {
		String res = "";
		
		if (l.getTk_not() != null) {
			res += l.getTk_not();
		}
		if (l.getLog_expr_factor() != null) {
			res += log_expr_factorToString(l.getLog_expr_factor());
		}
		if (l.getRel_expr() != null) {
			res += rel_expr(l.getRel_expr());
		}
		
		return res;
	}
	
	private String rel_expr(Rel_expr r) {
		String res = "";
		
		if (r.getArithm_exp() != null) {
			res += arithm_exprToString(r.getArithm_exp());
		} else if (r.getString() != null) {
			res += r.getString().getString();
		}
		
		if (r.getRelOp() != null) {
			res += r.getRelOp();
			
			if (r.getArithm_expr() != null) {
				res += arithm_exprToString(r.getArithm_expr());
			} else if (r.getString2() != null) {
				res += r.getString2().getString();
			} else if (r.getList() != null) {
				res += listToString(r.getList());
			} else if (r.getPlan_term() != null) {
				//TODO Plan_term
				res += "{..}";
			}
		}
		
		return res;
	}
	
	private String arithm_exprToString(Arithm_expr a) {
		String res = "";
		
		if (a.getArithm_expr_trm() != null) {
			res += arithm_expr_trmToString(a.getArithm_expr_trm());
		}
		
		if (a.getSubadd() != null) {
			res += a.getSubadd();
			
			if (a.getArithm_expr() != null) {
				res += arithm_exprToString(a.getArithm_expr());
			}
		}
		
		return res;
	}
	
	private String arithm_expr_trmToString(Arithm_expr_trm a) {
		String res = "";
		
		if (a.getArithm_expr_factor() != null) {
			res += arithm_expr_factorToString(a.getArithm_expr_factor());
		}
		
		if (a.getMuldiv() != null) {
			res += a.getMuldiv();
		} else if (a.getTk_intdiv() != null) {
			res += a.getTk_intdiv();
		} else if (a.getTk_intmod() != null) {
			res += a.getTk_intmod();
		}
		if (a.getArithm_expr_trm() != null) {
			res += arithm_expr_trmToString(a.getArithm_expr_trm());
		}
		
		return res;
	}
	
	private String arithm_expr_factorToString(Arithm_expr_factor a) {
		String res = "";
		
		if (a.getArithm_expr_simple() != null) {
			res += arithm_expr_simpleToString(a.getArithm_expr_simple());
		}
		
		if (a.getExponential() != null) {
			res += a.getExponential();
			
			if (a.getArithm_expr_factor() != null) {
				res += arithm_expr_factorToString(a.getArithm_expr_factor());
			}
		}
		
		return res;
	}
	
	private String arithm_expr_simpleToString(Arithm_expr_simple a) {
		String res = "";
		
		if (a.getNumber() != null) {
			res += numberToString(a.getNumber());
		} else if (a.getUnary() != null) {
			res += a.getUnary();
			if (a.getArithm_expr_simple() != null) {
				res += arithm_expr_simpleToString(a.getArithm_expr_simple());
			}
		} else if (a.getLog_expr() != null) {
			res += "(" + log_exprToString(a.getLog_expr()) + ")";
		} else if (a.getVar() != null) {
			res += varToString(a.getVar());
		} else if (a.getFunction() != null) {
			res += functionToString(a.getFunction());
		}
		
		return res;
	}
	
	private String functionToString(Function f) {
		String res = "";
		
		if (f.getLiteral() != null) {
			res += literalToString(f.getLiteral());
		}
		
		return res;
	}
	
	private String varToString(Var v) {
		String res = "";
		
		if (v.getVar() != null) {
			res += v.getVar();
		} else if (v.getUnnamedvar() != null) {
			res += v.getUnnamedvar();
		}
		
		if (v.getList() != null) {
			res += listToString(v.getList());
		}
		
		return res;
	}
	
	private String numberToString(Number n) {
		String res = "";
		
		String intPart = "";
		String decPart = "";
		if (n.getNumber() != null) {
			for (String s : n.getNumber()) {
				intPart += s;
			}
		}
		if (n.getDecimalPart() != null) {
			for (String s : n.getDecimalPart()) {
				decPart += s;
			}
		}
		
		if (decPart.equals("")) {
			res += intPart;
		} else {
			res += intPart + "." + decPart;
		}
		
		return res;
	}
	
	//Labels and icons can be computed like this:
    /*public String image(Object element) {
    	return "belief";
    }*/
}
