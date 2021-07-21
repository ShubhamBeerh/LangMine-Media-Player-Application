*************************************************INSTRUCTIONS FOR PROJECT BUILD AND CHANGES********************************************************************
----------------------------------------------------------------PART 1: INSTALLATIONS--------------------------------------------------------------------------
1)Install JDK 8   :-> https://www.oracle.com/in/java/technologies/javase/javase-jdk8-downloads.html
2)Install Eclipse :-> https://www.eclipse.org/downloads/
3)Go to Help/Eclipse MarketPlace and search for "fx" and install e(fx)clipse latest version.
4)Install JavaFX  :-> https://gluonhq.com/products/javafx/
5)Install Gluon Scene Builder :-> https://gluonhq.com/products/scene-builder/#download
----------------------------------------------------------------PART 2: BUILDING THE PROJECT--------------------------------------------------------------------
6)Import the project in Eclipse :-> File/Import/General/Projects from Folder or Archive/Directory/"location of download"
7)Go to Windows/Preferences/Java/BuildPath/UserLibraries.
	7.1) Create a user library with any name.
	7.2) Add External Jars and add all the jars from the lib folder of your extracted JavaFX folder of step 4.
8)Right click on the project/Build Path/ Configure Build Path/ Module Path
	8.1)Click on Add Library and select the library which you made in step 7.
9)Right click on the project.
	9.1)Click on Run as and then click on Run Configurations.
	9.2)Go to Arguments Tab.
	9.3)In VM Arguments, paste --module-path "YOUR JAVAFX LIB FOLDER PATH" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.graphics
	9.4)Click on Apply and then click on Run.
----------------------------------------------------------------PART 3:INTEGRATING GLUON SCENE BUILDER-----------------------------------------------------------
10)Go to Preferences
	10.1)Click on JavaFX
	10.2)Click on Browse and go to the installed location of your SceneBuilder application as done in step 5.
	10.3)Search for the SceneBuilder application,click on it and finally press OK.
	10.4)Apply and Close.
11)Right click on FXML file and select "Open with Scene Builder" and your FXML file will now be opened in your scene builder.
--------------------------------------------------------PART 4: FOREIGN FONTS CORRECTION FOR TRANSLATION---------------------------------------------------------
12)Open Environment Variables and go to System Variables.
13)Set Variable Name = JAVA_TOOL_OPTIONS and Variable Value = -Dfile.encoding=UTF8.
-----------------------------------------------------------------------------------------------------------------------------------------------------------------