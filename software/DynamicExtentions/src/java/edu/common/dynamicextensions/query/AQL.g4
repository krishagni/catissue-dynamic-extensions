grammar AQL;

query         : (SELECT select_list WHERE)? filter_expr limit_expr? (crosstab_expr | report_expr)? #QueryExpr
              ;
      
select_list   : DISTINCT? select_element (',' select_element)* #SelectExpr
              ;

select_element: arith_expr ('as' SLITERAL)?          #SelectElement
              ;

filter_expr   : filter_expr AND filter_expr          #AndFilterExpr
              | filter_expr OR  filter_expr          #OrFilterExpr
              | filter_expr PAND filter_expr         #PandFilterExpr
              | LP filter_expr RP                    #ParensFilterExpr
              | NTHCHILD LP filter_expr RP           #NthChildFilterExpr
              | NOT filter_expr                      #NotFilterExpr
              | filter                               #SimpleFilter
              ;

limit_expr    : LIMIT INT (',' INT)?                 #LimitExpr
              ;

crosstab_expr : CROSSTAB LP LP row+=INT (',' row+=INT)* RP ',' col=INT ',' LP value+=INT (',' value+=INT)* RP (',' BOOL)? RP #CrossTabExpr
              ;

report_expr   : ID (LP SLITERAL (',' SLITERAL)* RP)? #ReportExpr
              ; 

filter        : arith_expr  OP   arith_expr          #BasicFilter
              | arith_expr  MOP  literal_values      #MvFilter
              | FIELD       SOP  SLITERAL            #StringCompFilter
              | arith_expr  UOP                      #UnaryFilter
              | arith_expr  BETWEEN LP arith_expr ',' arith_expr RP #BetweenFilter
              ;
              
literal_values: '(' literal (',' literal)* ')'
              ;
              
literal       : SLITERAL                             #StringLiteral 
              | INT                                  #IntLiteral
              | FLOAT                                #FloatLiteral
              | BOOL                                 #BoolLiteral
              ;                            
	 
arith_expr    : arith_expr ARITH_OP arith_expr               #ArithExpr
              | arith_expr ARITH_OP date_interval            #DateIntervalExpr
              | LP arith_expr RP                             #ParensArithExpr
              | MTHS_BTWN LP arith_expr ',' arith_expr RP    #MonthsDiffFunc
              | YRS_BTWN LP arith_expr ',' arith_expr RP     #YearsDiffFunc
              | MINS_BTWN LP arith_expr ',' arith_expr RP    #MinsDiffFunc
              | CURR_DATE LP RP                              #CurrentDateFunc
              | agg_expr                                     #AggExpr
              | concat_expr                                  #ConcatExpr
              | ROUND LP arith_expr ',' INT RP               #RoundFunc
              | FIELD                                        #Field              
              | literal                                      #LiteralVal              
              ;	 

agg_expr      : (COUNT|SUM|MIN|MAX|AVG) LP DISTINCT? FIELD RP #AggFunc
              ;

concat_expr   : CONCAT LP arith_expr ',' arith_expr (',' arith_expr)* RP #ConcatFunc
              ;
          
date_interval : YEAR MONTH? DAY?
              | YEAR? MONTH DAY?
              | YEAR? MONTH? DAY
              ;          
                   
               
WS       : [ \t\n\r]+ -> skip;

SELECT   : 'select';
WHERE    : 'where';
NTHCHILD : 'nthchild';
BETWEEN  : 'between';
MTHS_BTWN: 'months_between';
YRS_BTWN:  'years_between';
CURR_DATE: 'current_date';
MINS_BTWN: 'minutes_between';
COUNT    : 'count';
SUM      : 'sum';
MIN      : 'min';
MAX      : 'max';
AVG      : 'avg';
DISTINCT : 'distinct';
LIMIT    : 'limit';
CROSSTAB : 'crosstab';
CONCAT   : 'concat';
OR       : 'or';
AND      : 'and';
PAND     : 'pand';
NOT      : 'not';
ROUND    : 'round';
LP       : '(';
RP       : ')';
MOP      : ('in'|'not in');
SOP      : ('contains'|'starts with'|'ends with');
UOP      : ('exists'|'not exists'|'any');
BOOL     : ('true'|'false');
OP       : ('>'|'<'|'>='|'<='|'='|'!='|'like');
INT      : '-'? DIGIT+;
FLOAT    : '-'? DIGIT+ '.' DIGIT+;
YEAR     : DIGIT+ ('y'|'Y');
MONTH    : DIGIT+ ('m'|'M');
DAY      : DIGIT+ ('d'|'D');
DIGIT    : ('0'..'9');
ID       : ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'?')*;
FIELD    : (INT|ID) '.' ID ('.' ID)*;
SLITERAL : '"' SGUTS '"';
ESC      : '\\' ('\\' | '"');
ARITH_OP : ('+'|'-'|'*'|'/');
ERROR    : .;

fragment
SGUTS    : (ESC | ~('\\' | '"'))*;
QUOTE    : '"';
