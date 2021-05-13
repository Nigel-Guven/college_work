import java.util;

public class OOProgStyle
{
	public static void main(String [] args)
	{
	    Task task1 = new Task();
	    Task task2 = new Task();
	    Event event1 = new Event();
	    Event event2 = new Event();
	   
	    Queue queue1 = new Queue();
	    
	    queue1.enqueue(task1);
	    queue1.enqueue(event1);
	    queue1.enqueue(event2);
	    queue1.enqueue(task2);
	    
	    queue1.dequeue();
	}
}

class Task
{
    Date date = null;
    StartTime startTime = null;
    Duration duration = null;
    Person [] people = null;
    
    public Task();
    
    Task(Date dmy,StartTime s,Duration d,Person [] p)
    {
        this.date = dmy;
        this.startTime = s;
        this.duration = d;
        this.people = p;
    }
}
	
class Event
{	
	Date date = null;
	StartTime startTime = null;
	Location location = null;

    public Event();
    
    public Event(Date d, StartTime s, Locationl)
    {
        this.date = d;
        this.startTime = s;
        this.location = l;
    }
}

class Person
{
		String name = null;
		
		public Person();
		
		public Person(String n)
		{
				this.name = n;
		}	
		
}	

class Location
{
		String place = null;
		
		public Location();
		
		public Location(String p)
		{
				this.place = p;
		}	
}

class Date 
{
	int day = null;
	int month = null;
	int year = null;
	
	public Date();
	
	public Date(int d,int m,int y)
	{
		this.day = d;
		this.month = m;
		this.year = y;
	}	
}

class StartTime
{
	int hour = null;
	int min = null; 
	
	public StartTime();
	
	public StartTime(int h, int m)
	{
	    this.hour = h;
	    this.min - m;
	}
}

class Duration
{
	int minutes = null;
	
	Duration();
	
	public Duration(int min);
	{
	   this.minutes = min; 
	} 
}
class Queue<Item> implements Iterable<Item>
{
    public static class Node<Item>
    {
        Item item;
        Node<Item> next;
    }
    
    Node<Item> first = null;
    Node<Item> last = null;
    int n;
    
    public Queue()
    {
        first = null;
        last = null;
        n = 0;
    }
    
    public boolean isEmpty()
    {
        return first == null;
    }
    public void enqueue(Item item)
    {
        Node<Item> oldLast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if(isEmpty()) 
            first = last;
        else
            oldLast.next = last;
        n++;     
    }
    public void dequeue()
    {
        if(isEmpty())
        {
            return "No elements to remove";
        }
        Item item = first.item;
        first = first.next;
        n--;
        if(isEmpty())
            last = null;
            
        return item;     
    }
}
