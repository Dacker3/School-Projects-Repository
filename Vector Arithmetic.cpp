//============================================================================
/**
* Recieves inputs for dimensions and coordinates of a vector and computes the magnitude, dot product, addition, subtraction, and multiplication
* Dosreis Acker
* <pre>
* Date: 4/21/2022
* Course: CSC 1253 section 1
* Programming Project #: 4
* Instructor: Dr. Duncan
* File: VectorManipulator.ccp
* </pre>
*/
//============================================================================
#include <iostream>
using namespace std;

void increment_all (int* start, int* stop)
{
  int * current = start;
  while (current != stop) {
    ++(*current);  // increment value pointed
    ++current;     // increment pointer
  }
}

void print_all (const int* start, const int* stop)
{
  const int * current = start;
  while (current != stop) {
    cout << *current << '\n';
    ++current;     // increment pointer
  }
}

int main ()
{
  int numbers[] = {10,20,30};
  increment_all (numbers,numbers+3);
  print_all (numbers,numbers+3);
  return 0;
}
