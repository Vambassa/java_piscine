rm -rf target

mkdir target

# directory for .class files
javac -d ./target src/java/edu/school21/printer/*/*.java

# launch program
java -classpath target edu.school21.printer.app.Program . 0 /Users/vambassa/Desktop/it.bmp