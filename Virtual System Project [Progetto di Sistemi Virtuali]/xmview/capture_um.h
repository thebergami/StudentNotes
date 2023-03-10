/*   This is part of um-ViewOS
 *   The user-mode implementation of OSVIEW -- A Process with a View
 *
 *   
 *
 *   Copyright 2005 Renzo Davoli University of Bologna - Italy
 *   Modified 2005 Ludovico Gardenghi
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
 *   $Id: capture_um.h 968 2011-08-03 10:16:26Z rd235 $
 *
 */
#ifndef CAPTURE_UM_H_
#define CAPTURE_UM_H_
#include "defs.h"

extern divfun scdtab[];
extern char scdnarg[];
#if __NR_socketcall != __NR_doesnotexist
extern divfun sockcdtab[];
#endif


extern int first_child_exit_status;
extern pid_t first_child_pid;
/* start a rc file */
void capture_execrc(const char *path,const char *argv1);
/* let the game start! */
int capture_main(char **argv, char *rc);
/* resume a process previously suspended */
void sc_resume(void *pc);

/* get the pcb of the current thread (thread safe) */
struct pcb *get_pcb();
/* set the pcb of the current thread */
void set_pcb(void *new);
/* just ask for the current size of the pcbtable */
int pcbtablesize(void);
/* This is the entry point for the guardian angels*/
int sub_angel(void *p);
/* This is the handler of sigchld from user processes */
int tracehand(struct pcb *orig_pcb);
/* pcb search (linear scan) */
struct pcb *pid2pcb(int pid);

int capture_attach(struct pcb *pc,pid_t pid);

#endif
