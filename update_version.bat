call mvn versions:set -DnewVersion=0.3.0
call mvn -N versions:update-child-modules
call mvn versions:commit