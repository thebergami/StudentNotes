/*   This is part of um-ViewOS
 *   The user-mode implementation of OSVIEW -- A Process with a View
 *
 *   gdebug.h: debugging functions (headers)
 *   
 *   Copyright 2005 Ludovico Gardenghi
 *   
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License, version 2, as
 *   published by the Free Software Foundation.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License along
 *   with this program; if not, write to the Free Software Foundation, Inc.,
 *   51 Franklin St, Fifth Floor, Boston, MA  02110-1301 USA.
 *
 *   $Id: gdebug.h 282 2007-01-18 12:33:03Z garden $
 *
 */   
#ifndef _GDEBUG_H
#define _GDEBUG_H

#include <stdio.h>
#include <string.h>

#ifdef DEBUG
#	define GDEBUG_ENABLED
#endif

#define GDEBUG_OFILE (gdebug_ofile?gdebug_ofile:stderr)
extern FILE* gdebug_ofile;

void gdebug_set_ofile(char* new_ofile);
void fgmsg(FILE *ofile, const char *fmt, ...);

#ifdef GDEBUG_ENABLED
#	ifndef GDEBUG_LEVEL
#		error "Debug enabled but GDEBUG_LEVEL undefined."
#	endif
#	define FGDEBUG(ofile, level, args...) fgdebug(ofile, GDEBUG_LEVEL, level, __FILE__, __LINE__, __func__, args)
#	define GDEBUG(level, args...) FGDEBUG(GDEBUG_OFILE, level, args)
#	define GPERROR(level, prefix) GDEBUG(level, "%s: %s", prefix, strerror(errno))
#	define FGHEXDUMP(ofile, level, text, len) fghexdump(ofile, GDEBUG_LEVEL, level, __FILE__, __LINE__, __func__, text, len)
#	define GHEXDUMP(level, text, len) FGHEXDUMP(GDEBUG_OFILE, level, text, len)
#	define GBACKTRACE(level, maxdepth) FGBACKTRACE(gdebug_ofile?gdebug_ofile:stderr, level, maxdepth)
#	define FGBACKTRACE(ofile, level, maxdepth) fgbacktrace(ofile, GDEBUG_LEVEL, level, __FILE__, __LINE__, __func__, maxdepth)
#	define FGERROR(ofile, args...) fgdebug(ofile, -1, -1, __FILE__, __LINE__, __func__, args)
#	define GERROR(args...) FGERROR(GDEBUG_OFILE, args)
#	define GMESSAGE(args...) FGERROR(GDEBUG_OFILE, args)

void fgdebug(FILE *ofile, int gdebug_level, int level, const char *file, const int line, const char *func, const char *fmt, ...);
void fghexdump(FILE *ofile, int gdebug_level, int level, const char *file, const int line, const char *func, char *text, int len);
void fgbacktrace(FILE *ofile, int gdebug_level, int level, const char *file, const int line, const char *func, int maxdepth);

#else
#	define FGDEBUG(ofile, level, args...)
#	define GDEBUG(level, args...)
#	define GPERROR(level, prefix)
#	define FGHEXDUMP(ofile, level, text, len)
#	define GHEXDUMP(level, text, len)
#	define GBACKTRACE(level, maxdepth)
#	define FGERROR(ofile, args...) fgmsg(ofile, args)
#	define GERROR(args...) FGERROR(GDEBUG_OFILE, args)
#	define GMESSAGE(args...) FGERROR(GDEBUG_OFILE, args)
#endif


#endif
