SRC = src
CLASS = classes
TEST = test

SRC_SUBPACKAGES = $(SRC)/zombicide/livrable/*.java

TEST_SUBPACKAGES =  $(TEST)/zombicide/actors/players/*.java \
 $(TEST)/zombicide/actions/*.java \
 $(TEST)/zombicide/actors/zombies/*.java \
 $(TEST)/zombicide/equipments/*.java \
 $(TEST)/zombicide/util/*.java \
 $(TEST)/zombicide/zone/*.java \
 $(TEST)/zombicide/zone/doors/*.java \

all: cls livrable1.jar livrable2.jar

cls:
	javac -sourcepath $(SRC) $(SRC_SUBPACKAGES) -d $(CLASS)

docs:
	javadoc -sourcepath $(SRC) -subpackages zombicide -d docs

comptest: cls
	javac -classpath "$(CLASS):junit-console.jar" $(TEST_SUBPACKAGES)

test: comptest
	java -jar junit-console.jar -classpath "$(TEST):$(CLASS)" -scan-classpath

livrable1.jar: cls
	jar cvfe jar/livrable1.jar zombicide.livrable.Livrable1 -C $(CLASS) zombicide

livrable2.jar: cls
	jar cvfe jar/livrable2.jar zombicide.livrable.Livrable2 -C $(CLASS) zombicide 

livrable3.jar : cls
	jar cvfe jar/livrable3.jar zombicide.livrable.Livrable3 -C $(CLASS) zombicide

zombicide.jar : cls
	jar cvfe jar/zombicide.jar zombicide.livrable.Zombicide -C $(CLASS) zombicide






