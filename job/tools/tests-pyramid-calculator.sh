#!/usr/bin/env bash
readonly JOB_MODULES_REGEX="*-job"
readonly WHERE_TO_SEARCH_JAVA_UNIT_TESTS="src/test/java"
readonly WHERE_TO_SEARCH_JAVA_INT_TESTS="src/test/java"
readonly WHERE_TO_SEARCH_FUNC_TESTS="src/functional/"

# Search all *Test.java files and calculate the number of @Test inside => unit tests
readonly JAVA_UNIT_TESTS=$(find $WHERE_TO_SEARCH_JAVA_UNIT_TESTS -name *Test.java ! -name *IntTest.java ! -name *FuncTest.java | xargs grep -w "@Test" | wc -l)

# Search all *IntTest.java files and calculate the number of @Test inside => integration tests
readonly JAVA_INT_TESTS=$(find $WHERE_TO_SEARCH_JAVA_INT_TESTS -name *IntTest.java | xargs grep -w "@Test" | wc -l)

# Search all *Test.java files, and calculate the number of tests inside => func tests
readonly JAVA_FUNC_TESTS=$(find $WHERE_TO_SEARCH_FUNC_TESTS -name *.feature | xargs grep -w "Scenario" | wc -l)

# Do the math!
readonly JAVA_TOTAL_TESTS=$((JAVA_UNIT_TESTS+JAVA_INT_TESTS+JAVA_FUNC_TESTS))

echo "==================="
echo "TEST COUNT"
echo "==================="
echo " -> Functional   :  $JAVA_FUNC_TESTS"
echo " -> Integration  :  $JAVA_INT_TESTS"
echo " -> Unit         :  $JAVA_UNIT_TESTS"

readonly JAVA_FUNC_MEAN=$(((JAVA_FUNC_TESTS)*100/JAVA_TOTAL_TESTS))
readonly JAVA_INT_MEAN=$(((JAVA_INT_TESTS)*100/JAVA_TOTAL_TESTS))
readonly JAVA_UNIT_MEAN=$(((JAVA_UNIT_TESTS)*100/JAVA_TOTAL_TESTS))

echo ""
echo "==================="
echo "TEST PYRAMID"
echo "==================="
echo " -> Functional   : ${JAVA_FUNC_MEAN}%"
echo " -> Integration  : ${JAVA_INT_MEAN}%"
echo " -> Unit         : ${JAVA_UNIT_MEAN}%"
