# Sound Of Sorting

**Sound Of Sorting** is designed to be an engaging yet educational tool for the demonstration and visualisation 
of a variety of sorting algorithms. 

The tool was inspired by Timo Bingmann's own 'Sound of Sorting' application.  
https://panthema.net/2013/sound-of-sorting/

To get started, either download the latest release or see **Build Instructions** to build the program yourself.

## Features

* 15 different sorting algorithms.

* Two distinct visualisation methods:
  * Bars  - The data is represented using a series of rectangular bars similar to those of a bar chart where the 
  data value corresponds to the height of the bar. In this mode a greater level of detail is displayed by highlighting 
  specific bars a different colour to represent 'special' values e.g. the boundary positions in a merge sort. 
  
  * Image - The data is represented as pixels in an image with the value being associated with the position / colour 
  of each pixel (bottom-left to top-right).

* In addition to visualising, the program will also generate an audible tone upon each comparison (and 
other operations), corresponding to the values of the data being compared.

* Speed and playback controls - Start, Pause, Step.

* Shuffle, reverse and preset options.

* Live updating statistics displaying various measurements relating to 
the current sort e.g. number of comparisons. At the end of a sort, these statistics are output to a CSV file 
for later comparison.

### Implemented Algorithms

Bogo, Bozo, Bubble, Cocktail, Comb, Exchange, Gnome, Insertion, Merge, OddEven, Pancake, Quick, Selection, Shell, Slow

## Quick Start

The program is designed to be easy and intuitive to use with the window divided into two sections - the 
visualiser and the settings panel.

To get started expand the ***Sort*** dropdown to select an algorithm and adjust the ***Size*** slider to the 
desired array size. Press the ***Start*** button to begin the sort. (It is not necessary to manually shuffle 
the array before sorting.)

To change the delay between operations, adjust the ***Speed*** slider between 0ms and 100ms. (A value of 0 may 
be too fast for most array sizes and sorting algorithms.)

There are two modes for the visualiser - Bars and Image. Press the ***Render Switch*** button to switch between 
the two.

To add an image file to the program simply drag and drop the file onto the program window.  
**Note the image can be _atmost_ 4096 pixels (64x64).**

### Keybinds

* Enter - Start  
* Space - Step  
* P - Pause  
* R - Reset  
* M - Mute  
* Backspace - Random  
* E - Render mode  
* C - Clear error

The arrow keys can also be used to increment / decrement the sliders and scroll the dropdowns.

## Build Instructions

To build the .jar yourself:
* Clone the repository `git clone https://github.com/Tangent5969/Sorting.git`  
* Navigate to 'Sorting' `cd Sorting/Sorting`  
* Build `gradlew desktop:dist`

The .jar file is located at `Sorting\Sorting\desktop\build\libs\desktop-1.0.jar`
