/*   This is part of um-ViewOS
 *   The user-mode implementation of OSVIEW -- A Process with a View
 *
 *   
 *
 *   Copyright 2005 Renzo Davoli University of Bologna - Italy
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
 *   $Id: libummod.h 649 2009-04-23 05:44:01Z rd235 $
 *
 * 
 */
#ifndef _LIBUMMOD_H
#define _LIBUMMOD_H
#include <unistd.h>
#include <linux/types.h>
#include <sys/types.h>
#include <linux/unistd.h>
#include <linux_dirent.h>
#include <errno.h>

int getdents(unsigned int fd, struct dirent *dirp, unsigned int count);

int getdents64(unsigned int fd, struct dirent64 *dirp, unsigned int count);

#if !defined(__x86_64__)
int fcntl32(int fd, int cmd, long arg);
#else
#define fcntl32 fcntl
#endif

int fcntl64(int fd, int cmd, long arg);

int _llseek(unsigned int fd, unsigned long offset_high,
		unsigned long offset_low, loff_t *result, unsigned int whence);

#ifdef __NR_chown32
#undef __NR_chown
#define __NR_chown __NR_chown32
#endif
#ifdef __NR_lchown32
#undef __NR_lchown
#define __NR_lchown __NR_lchown32
#endif
#ifdef __NR_fchown32
#undef __NR_fchown
#define __NR_fchown __NR_fchown32
#endif
#ifdef __NR_getuid32
#undef __NR_getuid
#define __NR_getuid __NR_getuid32
#endif
#ifdef __NR_getgid32
#undef __NR_getgid
#define __NR_getgid __NR_getgid32
#endif
#ifdef __NR_geteuid32
#undef __NR_geteuid
#define __NR_geteuid __NR_geteuid32
#endif
#ifdef __NR_setreuid32
#undef __NR_setreuid
#define __NR_setreuid __NR_setreuid32
#endif
#ifdef __NR_setregid32
#undef __NR_setregid
#define __NR_setregid __NR_setregid32
#endif
#ifdef __NR_getgroups32
#undef __NR_getgroups
#define __NR_getgroups __NR_getgroups32
#endif
#ifdef __NR_getresuid32
#undef __NR_getresuid
#define __NR_getresuid __NR_getresuid32
#endif
#ifdef __NR_getresgid32
#undef __NR_getresgid
#define __NR_getresgid __NR_getresgid32
#endif
#ifdef __NR_setresuid32
#undef __NR_setresuid
#define __NR_setresuid __NR_setresuid32
#endif
#ifdef __NR_setresgid32
#undef __NR_setresgid
#define __NR_setresgid __NR_setresgid32
#endif
#ifdef __NR_setuid32
#undef __NR_setuid
#define __NR_setuid __NR_setuid32
#endif
#ifdef __NR_setgid32
#undef __NR_setgid
#define __NR_setgid __NR_setgid32
#endif
#ifdef __NR_setfsuid32
#undef __NR_setfsuid
#define __NR_setfsuid __NR_setfsuid32
#endif
#ifdef __NR_setfsuid32
#undef __NR_setfsuid
#define __NR_setfsuid __NR_setfsuid32
#endif
#ifdef __NR_getegid32
#undef __NR_getegid
#define __NR_getegid __NR_getegid32
#endif
#ifdef __NR_setfsgid32
#undef __NR_setfsgid
#define __NR_setfsgid __NR_setfsgid32
#endif

/*#if defined(__x86_64__)
#define __NR__newselect __NR_select
#endif
*/

#endif
