call mvn versions:set -DnewVersion=0.2.0-SNAPSHOT
call mvn -N versions:update-child-modules
call mvn versions:commit