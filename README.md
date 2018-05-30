# Towers
a towers of hanoi simualtion program

Author: Karan Joshi
Program Name: Towers
Iteration: 3
Summary: Tower's of Hanoi simulation system with special functionality.

Run Instructions:
- navigate to src->sample->Main.class
- Run the main method in the Main.class
- JavaFX will allow for more intuitive interactions
- up to this point this is all back-end functionality

Changes:
- renamed "InputOutputLogic" package to IO_Utils
- refactored project to use JUnit4 instead of JUnit 5
- All tests now use JUnit4 referenced imports
- HanoiSim.HanoiSim.displayDisks() calls upon display()
- HanoiSim.HanoiSim.displayTowers() calls upon display()
- HanoiSim.HanoiSim.display() is calls upon DisplayUtil generic class to use generic display functionality
- moved analyzeFileData from HanoiSim.HanoiSim to IO_Utils.HanoiFileLogic class
- moved getProjectedDisks from HanoiSim.HanoiSim to IO_Utils.HanoiFileLogic class
- added getDisplay() function to HanoiObject to enable generic display implementation

Additions:
- IO_Utils.MethodTimer class that allows the timing of the simulation run
- IO_Utils.DisplayUtil class that allows displaying of the different objects in the simulation
- TestIO_Utils package that houses tests for the IO_Utils package
