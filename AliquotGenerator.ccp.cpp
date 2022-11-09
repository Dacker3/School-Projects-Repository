/**
* Computes the aliquot sum of a number
* Dosreis Acker
* <pre>
* Date: 3/23/2022
* CSC 1253 Project # 3 Section # YOUR SECTION NUMBER<br>
* Instructor: Dr. Duncan
* File: AliquotGenerator.cpp
* </pre>
*/

#include <iostream>

using namespace std;
/**
* Computes the aliquot sum of the specified number
* @param num an integer
* @return the aliquot sum of the specified number
* if it is positive; otherwise, -1;
*/
long rSigma(long num)
{
    if (num > 0)
    {
        int aliquot_sum = 0;
        for (int i=1; i<=num/2; i++)
        {
            if (num % i == 0 ) aliquot_sum += i;
        }
        return aliquot_sum;
    }
    else return -1;
}
/**
* Generates the string representation of a series whose terms
* are increasing and consist of proper divisors of the specified number.
* @param num an integer
* @return a string representation of the series when num is
* greater than 1, "0" when num is 1; otherwise "nan"
*/

string genRSigSeries (long num)
{
    string series=" = ";
    if (num<=0)
        return"nan";
    if (num==1)
        return "0";
    if (rSigma(num)==1)
        return series;
    series+="1";
    for (int i=2; i<= num/2; i++)
    {
        if (num % i == 0)
            series+=( " + " + to_string(i));
    }
    series+=" = ";
    return series;
}
/**
* Generates the aliquot sequence of a number and determines the
* length of the sequence.
* @param num an integer
* @param sequence the aliquot sequence of the specified number when
* num is positive; otherwise "nan".
* @param length the length of the aliquot sequence of the specified
* number when num is positive; otherwise 0
*/
void aliquot(long num, string& sequence, long& length)
{
    sequence=to_string(num);
    length=1;
    while(num>0)
    {
        sequence+=(","+to_string(rSigma(num)));
        num=rSigma(num);
        length++;
    }
    return;
}

int main()
{
   long int num1;
   long aliquot_sum, num;
   cout<< "Enter positive integer for its classification -->";
   cin>> num1;
   if (num1 > 0)
   {
      cout << "s(" << num1 << ")";
      cout << genRSigSeries(num1) << rSigma(num1) << endl;
      cout << "s("<<num1<<") ";
   if (num1 == rSigma(num1))
    {
    cout<< "= " << num1<<endl;
    cout<<num1<<" is a perfect number."<<endl;
    }
    else if (num1<rSigma(num1))
    {
    cout<<"> " << num1<<endl;
    cout<<num1<<" is an abundant number."<<endl;
    }
    else
    {
    cout << "< " << num1<<endl;
    cout<<num1<<" is a deficient number."<<endl;
    }
   }
   else
   cout<<"ERROR: The input must be a positive integer."<< endl;

 int n1, n2; //Two positive integers
    cout << "Enter two positive integers ->";
    cin >> n1 >> n2;
    if ( (n1 <= 0 || n2 <= 0) || ( n1 == n2) )
    {
        cout << "ERROR: The inputs must be distinct positive integers." << endl;
    }
    else
    {
        cout<< "?amicable("<<n1<<","<<n2<<") = ";
        if (rSigma(n1)==n2)
        {
            cout<<"True"<<endl;
            cout<<"s("<<n1<<") = "<<genRSigSeries(n1)<<" = "<<rSigma(n1)<<endl;
            cout<<"s("<<n2<<") = "<<genRSigSeries(n2)<<" = "<<rSigma(n2)<<endl;
        }
        else
        {
           cout<<"False"<<endl;
            cout<<"s("<<n1<<") = "<<genRSigSeries(n1)<<" = "<<rSigma(n1)<<endl;
            cout<<"s("<<n1<<") = "<<genRSigSeries(n2)<<" = "<<rSigma(n2)<<endl;
        }
    }
int n3;
string s;
long length;
cout<<"Enter an imperfect positive integer -->";
cin>>n3;
{
  if (num<=0 || rSigma(n3)==n3)
  {
    cout<<"ERROR: The input must be an imperfect positive integer.";
    return 0;
  }
  else
    {
        cout<<"aliquot{"<<n3<<"} = ";
        aliquot(n3, s, length);
        cout<<s<<endl;
        cout<<"length = "<<length<<endl;
    }
}
    return 0;
}
