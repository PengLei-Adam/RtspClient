#!/bin/sh
# AUTO-GENERATED FILE, DO NOT EDIT!
if [ -f $1.org ]; then
  sed -e 's!^C:/cygwin64/lib!/usr/lib!ig;s! C:/cygwin64/lib! /usr/lib!ig;s!^C:/cygwin64/bin!/usr/bin!ig;s! C:/cygwin64/bin! /usr/bin!ig;s!^C:/cygwin64/!/!ig;s! C:/cygwin64/! /!ig;s!^Q:!/cygdrive/q!ig;s! Q:! /cygdrive/q!ig;s!^J:!/cygdrive/j!ig;s! J:! /cygdrive/j!ig;s!^I:!/cygdrive/i!ig;s! I:! /cygdrive/i!ig;s!^H:!/cygdrive/h!ig;s! H:! /cygdrive/h!ig;s!^F:!/cygdrive/f!ig;s! F:! /cygdrive/f!ig;s!^E:!/cygdrive/e!ig;s! E:! /cygdrive/e!ig;s!^D:!/cygdrive/d!ig;s! D:! /cygdrive/d!ig;s!^C:!/cygdrive/c!ig;s! C:! /cygdrive/c!ig;' $1.org > $1 && rm -f $1.org
fi
