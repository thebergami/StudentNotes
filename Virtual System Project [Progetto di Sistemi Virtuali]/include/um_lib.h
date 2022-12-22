/*   This is part of um-ViewOS
 *   The user-mode implementation of OSVIEW -- A Process with a View
 *
 *   library header for user level access to um-ViewOS service mgmt
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
 *   $Id: um_lib.h 913 2011-01-06 17:46:23Z rd235 $
 *
 */
#ifndef _UM_LIB_H
#define _UM_LIB_H
#include <sys/utsname.h>
#include <sys/types.h>
#include <unistd.h>
typedef unsigned long viewid_t;

#define VIRUMSERVICE 1

#define ADD_SERVICE 0
#define DEL_SERVICE 1
#define LIST_SERVICE 3
#define NAME_SERVICE 4

#define RECURSIVE_VIEWOS   0x100
#define VIEWOS_GETINFO     0x101
#define VIEWOS_SETVIEWNAME 0x102

#define VIEWOS_KILLALL     0x103

#define VIEWOS_ATTACH      0x104
#define VIEWOS_FSALIAS     0x105

extern long (*virnsyscall)();

#define virsyscall0(virscno,a1) virnsyscall(virscno,0,0,0,0,0,0,0);
#define virsyscall1(virscno,a1) virnsyscall(virscno,1,(a1),0,0,0,0,0);
#define virsyscall2(virscno,a1,a2) virnsyscall(virscno,2,(a1),(a2),0,0,0,0);
#define virsyscall3(virscno,a1,a2,a3) virnsyscall(virscno,3,(a1),(a2),(a3),0,0,0);
#define virsyscall4(virscno,a1,a2,a3,a4) virnsyscall(virscno,4,(a1),(a2),(a3),(a4),0,0);
#define virsyscall5(virscno,a1,a2,a3,a4,a5) virnsyscall(virscno,5,(a1),(a2),(a3),(a4),(a5),0);
#define virsyscall6(virscno,a1,a2,a3,a4,a5,a6) virnsyscall(virscno,5,(a1),(a2),(a3),(a4),(a5),(a6));

struct viewinfo {
	struct utsname uname;
	pid_t serverid;
	viewid_t viewid;
	char viewname[_UTSNAME_LENGTH];
};

int um_check_viewos(void);
int um_add_service(char *path,int permament);
int um_del_service(char *name);
int um_list_service(char *buf, int len);
int um_name_service(char *name, char *buf, int len);
int um_view_getinfo(struct viewinfo *info);
int um_setviewname(char *name);
int um_killall(int signo);
int um_attach(int pid);
int um_fsalias(char *alias,char *filesystemname);

#endif
