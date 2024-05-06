<h1 align="center">Tugas Kecil 3 IF2211 Strategi Algoritma</h1>
<h2 align="center">Semester II tahun 2023/2024</h2>
<h3 align="center">Penyelesaian Permainan Word Ladder Menggunakan Algoritma UCS, Greedy Best First Search, dan A*</p>

## Table of Contents

- [Short Description](#short-description)
- [Creator](#creator)
- [Built With](#built-with)
- [Prerequisites](#prerequisites)
- [How to run](#how-to-run)
- [Guides](#guides)
- [Application Overview](#application-overview)
- [Links](#links)

## Short Description
This program is designed to solve the famous word game called the Word Ladder Game. It's created as a project assignment for the course IF2211 Algorithm Strategy. Additionally, it serves as a handy tool for players who get stuck and seek the optimal solution during the Word Ladder game.

It's worth noting that if users can't find a solution or if the solution provided doesn't match the game's rules, they can modify the dictionary used by the program located in `./src/utils` and change the path in `./WordLadderGUI.java` file in the Dictionary initialization `line 105`. Users have the flexibility to add or change the path to the targeted dictionary as needed.

To achieve its solving capabilities, the programmer implemented three search algorithms Uniform Cost Search Algorithm, Greedy Best First Search Algorithm, and A* algorithm. The reason behind programmer using these algorithms is to compare their performance and analyze factors such as memory usage, complexity, and execution time. This comparison is in order to determine which algorithm best suits different problem sets.

The current dictionary used by this program can be found [here](https://docs.oracle.com/javase/tutorial/collections/interfaces/examples/dictionary.txt), while the reference for the game can be accessed [here](https://wordwormdormdork.com/).

## Creator
| NIM      | Nama                    | Kelas                                                                                                                                                                                                               |
|----------|-------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 13522045 | Elbert Chailes    | K-01                                                            |

## Built With
- [Java](https://docs.oracle.com/en/java/)
- [Java Swing](https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/javax/swing/package-summary.html)

## Prerequisites
- For Windows users: Ensure `Java 19` or a newer version is installed on your computer to successfully compile and execute the code.
- For Linux users: Install the `most recent version` of Java on your computer to compile and execute the code. Verify that the installed Java version supports graphical interfaces, or you can install it using the command `sudo apt install openjdk-19-jdk`.

## How to run
If you want to run this program you will need to do these steps

1. Clone this repository :
```shell
git clone https://github.com/ChaiGans/Tucil3_13522045.git
```

2. Navigate to the `src` directory:
```shell
cd .\Tucil3_13522045\
cd src
```

3. Compile and run Java bytecode by writing this into terminal :
- For GUI (Windows)
```shell
./build.bat
```
- For GUI (Linux)
```shell
chmod +x ./build.sh
./build.sh
```
- For CLI (Windows)
```shell
./cli.bat
```
- For CLI (Linux)
```shell
chmod +x ./cli.sh
./cli.sh
```

## Guides
1. The user inputs the starting word and the ending word for the game to find a solution. Ensure that the input words are defined in the dictionary.
2. The user selects one of the radio buttons (UCS, GBFS, A*) to choose which algorithm to use for solving the problem.
3. The result will be displayed in the result box area, showing the best solution for each algorithm.
   
In conclusion:
- For fast execution time without guaranteeing the optimal path, use the GBFS (Greedy Best First Search) algorithm.
- For seeking the optimal solution with average execution time, use the A* algorithm.

## Application Overview
![image](https://github.com/ChaiGans/Tucil3_13522045/assets/113753352/f7c7ddd8-b31f-448e-b295-05654514ea8e)
![image](https://github.com/ChaiGans/Tucil3_13522045/assets/113753352/5dad9484-9e5e-427c-aec3-9ac2350b6bd7)
![image](https://github.com/ChaiGans/Tucil3_13522045/assets/113753352/6ea27cce-3526-4a5a-ab63-5fcc46621b33)

## Links
- Repository : https://github.com/ChaiGans/Tucil3_13522045
- Issue tracker :
   - If you encounter any issues with the program, come across any disruptive bugs, or have any suggestions for improvement, please don't hesitate to reach out by sending an email to elbertchailes888@gmail.com. Your feedback is greatly appreciated.
