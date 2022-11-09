//============================================================================
// Name        : QuadraticSolver.cpp
// Author      : Dosreis Acker
// Version     :
// Copyright   : Your copyright notice
// Description : Quadratic Solver in C++
//============================================================================

#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

int main()
{
	double quadratic, linear, constant;
	cout<< "Enter a, b, c for the funtion f(x) = ax^2 + bx + c --->" ;
	cin>>quadratic>>linear>>constant;
	if (quadratic==0)
		cout<<"ERROR: The coefficents of the quadratic term must be non-zero.";
	else
    {
	    if(quadratic==1)
		    cout<<"x^2 ";
	    else if (quadratic==-1)
		    cout<<"-x^2 ";
	    else
		    cout<< "f(x)="<<quadratic<<"x^2 ";

	    if(linear!=0)
	    {
	        if(linear==1)
			        cout<<" + x";
		        else if(linear==-1)
			        cout<<" - x";
		        else if (linear>0)
			        cout<<fixed<<setprecision(3)<< "+ "<<linear<<"x ";
			    else
			        cout<<fixed<<setprecision(3)<< "- "<<linear<<"x ";
	    }

	    if (constant!=0)
	        cout<<fixed<<setprecision(3)<<"+ "<<constant;

        double discriminant=linear*linear-4*quadratic*constant, axisofsymmetry=-linear/(2*quadratic), x2=-(pow(linear,2)/(4*quadratic))+constant, irrat=sqrt(abs(discriminant))/(2*quadratic);

        cout<<endl<<"Discriminant:"<<fixed<<setprecision(3)<<discriminant<<endl;
        cout<<"Axis of Symmetry:"<<fixed<<setprecision(3)<<axisofsymmetry<<endl;
        cout<<"Vertex:"<<fixed<<setprecision(3)<<"("<<axisofsymmetry<<","<<x2<<")"<<endl;
        cout<<fixed<<setprecision(3)<<"y-intercept:(0.000,"<<constant<<")"<<endl;
        if (discriminant==0)
            cout<<"The roots of the Equation f(x) = 0.000: x = ("<<fixed<<setprecision(3)<<axisofsymmetry<<")";
        else if (discriminant>0)
            cout<<"The roots of the Equation f(x) = 0.000: x = ("<<fixed<<setprecision(3)<<axisofsymmetry+irrat<<","<<axisofsymmetry-irrat<<")";
        else
        {
            if (axisofsymmetry==0 && irrat==1)
                cout<<"The roots of the Equation f(x) = 0.000: x = "<<"(-i,i)"<<endl;
            else if (axisofsymmetry==0 && irrat!=1)
                cout<<"The roots of the Equation f(x) = 0.000: x = "<<fixed<<setprecision(3)<<"(-"<<irrat<<"i,"<<irrat<<"i)"<<endl;
            else if (axisofsymmetry!=0 && irrat==1)
                cout<<"The roots of the Equation f(x) = 0.000: x = "<<fixed<<setprecision(3)<<"("<<axisofsymmetry<<" - i"<<","<<axisofsymmetry<<" + i)"<<endl;
            else
                cout<<"The roots of the Equation f(x) = 0.000: x = "<<fixed<<setprecision(3)<<"("<<axisofsymmetry<<"-"<<irrat<<","<<axisofsymmetry<<"+"<<irrat<<")"<<endl;
        }
     }
    return 0;
}
}
