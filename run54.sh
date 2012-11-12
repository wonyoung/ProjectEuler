#Run script for the demo console app

#cd into the home directory - so you can run the script from vim or anywhere else
cd ~/projects/ProjectEuler/

#Notice that the DEX version of the .jar is used with java
#java -jar ./dist/Problem54PokerGame.dex.jar com.lge.euler.Problem54PokerGame
java -jar ./dist/Problem54PorkerGame.dex.jar:./libs/junit.dex.jar:./libs/guava-13.0.1.dex.jar:./libs/org.hamcrest.core_1.1.0.v20090501071000.dex.jar org.junit.runner.JUnitCore com.lge.euler.Problem54PorkerGame
#java -v -jar ./dist/Problem54PokerGame.dex.jar:./libs/junit.dex.jar:./libs/guava-13.0.1.dex.jar com.lge.euler.Problem54PokerGame

