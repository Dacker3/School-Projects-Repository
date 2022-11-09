//============================================================================
// Name        : IntPowerCalculator(2).cpp
// Author      : Dosreis Acker
// Date        : 5/7/2022
// Version     :
// Instructor  : Dr. Duncan
// Description : Caculates the power of an integer
// File        : IntPowerCaculator.cpp
// </pre>
//============================================================================

#include <iostream>
#include <iomanip>


using namespace std;

int main()
{
    double base, power;
    int exp, i;
    //Read data
    cout << "Enter the base and integer exponent of the power -> ";
    cin >> base >> exp;
    if(base == 0.0)
    { // If base is 0
        if(exp <= 0) // If n <= 0 then indeterminate
            cout << base << "^" << exp << " = nan";
        else // else 0
            cout << base << "^" << exp << " = 0.00000";
    }
    else{
        power = 1.0;
        if(exp >= 0){ // If n >= 0
            for(i = 1; i <= exp; i++)
                power *= base; // Calculate and display
            cout << base << "^" << exp << " = " << fixed << setprecision(5) << power ;
        }
        else{
            for(i = 1; i <= 0 - exp; i++)
                power *= base;
            cout << base << "^" << exp << " = " << fixed << setprecision(5) << 1.0 / power ;
        }
    }
}