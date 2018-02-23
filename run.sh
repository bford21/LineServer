# cd $(dirname $0)

# mvn clean compile
# ret=$?
# if [ $ret -ne 0 ]; then
# exit $ret
# fi
# rm -rf target

# ./gradlew compileJava
# ret=$?
# if [ $ret -ne 0 ]; then
# exit $ret
# fi
# rm -rf build

# cd ../complete
# mvn clean package

# rm -rf target

# ./gradlew build
# ret=$?
# if [ $ret -ne 0 ]; then
# exit $ret
# fi

# rm -rf build
# exit
mvn spring-boot:run