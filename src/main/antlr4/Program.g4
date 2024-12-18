grammar Program;

program: (simpleInstruction)+ EOF;

simpleInstruction
    : NAME args EOI;

args
    : arg (COMM arg)*
    ;
arg : VALID_ARG;

NAME: (LD | ST | INIT);
LD : ('load' | 'LOAD' | 'LD' | 'ld');
ST : ('store' | 'STORE' | 'ST' | 'st');
INIT : ('init' | 'INIT');
COMM : ',';
EOI : ';';

VALID_ARG : [0-9] [0-9]*;
WS: [ \t\n\r]+  -> channel(HIDDEN);