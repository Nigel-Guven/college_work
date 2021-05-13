#include <iostream>
#include <string>
using namespace std;
///////////////////////////////////////////

//PREAMBLE FOR QUEUE
#define LEN 50

String queue[LEN];
string rear = "bottom";
string front = "top";
int itemCount = 0;
//////////////////////////////////////////
int main()
{
	string task1 = "15/11/2017 <15:00> <70mins> <Mary, John, Joe>";
	string event1 = "13/11/2017 <15:00> <Dublin>";
	string task2 = "17/11/2017 <20:00> <35mins> <David, Sam, Brian>";
	string event2 = "12/11/2017 <17:45> <Cork>";
	
	string task3 = "";
	string event3 = "";
	
	cout << "Please enter a task in the format: dd/mm/yy <Start:Time> <Xmins> <Person1, Person2, ...>";
	cin >> task3;
	cout << "Please enter an event in the format: dd/mm/yy <Start:Time> <Location>";
	cin >> event3;
	
	///////////////////////////////////////////// 
	
	// IMPLEMENTATION FOR QUEUE
	
	bool isEmpty()
	{
		return itemCount == 0;
	}
	bool isFull()
	{
		return itemCount == LEN;	
	}	
	void enqueue(string str1)
	{
		if(!isFull)
		{
			if(rear == LEN-1)
			{
				rear = "bottom";
			}	
		}
		
		queue[++rear] = str1;
		itemCount++;
	}	
	
	string dequeue()
	{
		string x = queue[front++];
		
		if(front == LEN)
		{
			front = "top";
		}
		
		itemCount--;	

		
	}	
	
	////////////////////////////////
	//ENQUEUE & DEQUEUE TASK/EVENT//
	////////////////////////////////
	
	enqueue(task1);
	enqueue(task3);
	enqueue(event2);
	enqueue(event3);
	dequeue();
	dequeue();
	
	
	
	
	
	
	
	
	
}	