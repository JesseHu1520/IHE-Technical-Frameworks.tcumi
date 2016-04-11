call mvn versions:set -DnewVersion=0.4.1-SNAPSHOT
call mvn -N versions:update-child-modules
call mvn versions:commit