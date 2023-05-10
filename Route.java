import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

    /*
    * Created by
    *   Dos Acker 
    */

public class Route {

    public static void main(String[] args) throws FileNotFoundException {

        File airportInput = new File("airports.txt");
        File flightInput = new File("flights.txt");

        Scanner ASCAN  = new Scanner(airportInput);
        Scanner FSCAN = new Scanner(flightInput);

        Route setup = new Route(1000, 1000);
        
        ASCAN.nextLine();
        while (ASCAN.hasNextLine()) {
            String nextAirport = ASCAN.nextLine();
            
            Airport airport = new Airport(nextAirport);
            
            insertHash(airport);

        }
        
        Airport sourceAirport = hashArray[myHash(args[0])];
        Airport destAirport = hashArray[myHash(args[1])];
        int beginTime = Integer.parseInt(args[2]);

        int numFlights = Integer.parseInt(FSCAN.nextLine()) + 2;

        FSCAN.nextLine();
        // FSCAN.nextLine();

        int count = 0;
        while (count < 13950) {
            // AIRLINE	FLIGHT_NUMBER	ORIGIN_AIRPORT	DESTINATION_AIRPORT	
            // SCHEDULED_DEPARTURE	SCHEDULED_ARRIVAL	DISTANCE
            String next = FSCAN.nextLine();
            String[] things = next.split("\t");

            Airport origin = getAirport(things[2]);
            Airport dest = getAirport(things[3]); 
            int departTime = Integer.parseInt(things[4]);   
            int arriveTime = Integer.parseInt(things[5]);

            // add to flightsList
            Flight newFlight  = new Flight(origin, dest, departTime, arriveTime);
            origin.addFlight(newFlight);

            count++;
        }
           

        int minTime = dijkstraFastestTime(sourceAirport, destAirport, beginTime);
        System.out.println(minTime);

        ASCAN.close();
        FSCAN.close();

    }

    private static Airport[] hashArray;
    private static int hashSize;
    private static Airport[] heapArray; // heap stores the vertices in increasing order
                                 // of their distance/(time?) from the source vertex
    private static int arraySize;  // max array size, CAPACITY
    private static int heapSize;   // current heap size, set to 0

    public Route(int hashSize,int arraySize) {
        hashArray = new Airport[hashSize];
        this.hashSize = hashSize;
        
        this.arraySize = arraySize;
        heapArray = new Airport[arraySize];
        this.heapSize = 0;
    }

    private static int myHash(String code) {
        
        int p0 = code.charAt(0) - 'A' + 1;
        int p1 = code.charAt(1) - 'A' + 1;
        int p2 = code.charAt(2) - 'A' + 1;
        int p3 = p0 * 467 * 467 + p1 * 467 + p2;
        int p4 = p3 % 7193;
        return p4 % 1000;
    }
    
    public static void insertHash(Airport airport) {

        airport.hashPos = myHash(airport.code);

        while (!(hashArray[airport.hashPos] == null)) {
            airport.hashPos++;
        }
        hashArray[airport.hashPos] = airport;
        hashSize++;

    }
    public static int dijkstraFastestTime(Airport s, Airport d, int beginTime) {

        for (Airport v : hashArray) {
            if (v != null) {
                if (v == s) {
                    v.dValue = 0;
                }
                else {
                    v.dValue = Integer.MAX_VALUE; //infinity
                    v.parent = null;
                }
                insert(v);
            }
        }
        while (heapSize > 0) {    // while heap is not empty
            Airport u = extractMin();
            for (Flight e : u.flightsList) {
                if (e.departTime > beginTime && e.origin == s && e.dest == d) {
                        return e.arriveTime;
                }
                else {
                    if (e.departTime > beginTime){
                        Relax2(e);
                    }

            }
        }
    }
        //return d.dValue;
        return d.parentFlight.arriveTime;
    }
    private static void relax(Flight e, int beginTime) { // e = edge 
        // relaxes an edge
        Airport v = e.origin; // v = e's parent vertex or origin
        Airport p = e.dest; // p destination vertex

        int newDValue;
        if (e.departTime < beginTime) {
            newDValue = v.dValue;
        }
        else {
            newDValue = v.dValue + e.arriveTime - e.departTime;
        }
        if(newDValue < p.dValue) {  
            p.dValue = newDValue;
            p.parent = v;
            p.parentFlight = e;
            if (v.heapPos == -1) { // not in the heap
                insert(p);
            }
            else {
                decreaseKey(heapArray, p.heapPos);
            }
        }
    }


    private static void Relax2(Flight e) {
        Airport origin = e.origin;
        if (e.departTime < origin.dValue) {
            return;
        }
        Airport dest = e.dest;
        if (dest.dValue > e.arriveTime) { 
            decreaseKey2(dest, arraySize);
            dest.parentFlight = e;
        }

    }

    private static Airport getAirport(String code) {
        // gets the Airport from the hash table
        int p = myHash(code);
        if (hashArray[p] == null) { 
            return null;
        }
        if (hashArray[p].code.equals(code)) {
            return hashArray[p];
        }
        for(int i = p + 1; (i != p); i++) {
            if (i >= 1000) {
                i = i % 1000; //rolling over
            }
            
            if (hashArray[i] == null) {
                return null;
            }
            if (hashArray[i].code.equals(code)) {
                return hashArray[p];
            }
        }
        return null; 
    }

    private static void insert(Airport v) {         // inserts in heap

        if (heapSize < arraySize) {
            heapArray[heapSize] = v;
            v.heapPos = heapSize;
            floatUp(heapArray, heapSize);
            heapSize++;
        }
        else {
            throw new IllegalArgumentException("Heap is full");
        }
    }

    private static Airport extractMin() {
        Airport min = heapArray[0];

        swap(0,heapSize-1);
        heapSize--;
        sinkDown(0, heapSize-1);

        return min;
    }

    private static void floatUp(Airport heapArray[], int child) {
        // need to add updating heapPos
        
          int parent = (child - 1) / 2;
          if (child  == 0) { // child is the root, so it has no parent
               return; 
          }
          if (heapArray[parent].dValue > heapArray[child].dValue) { // if the time to get to the child is shorter
                                              // than to get to the parent, swap em 
            swap(parent, child);
            
            int tempPos = heapArray[parent].heapPos;
            heapArray[parent].heapPos = heapArray[child].heapPos;
            heapArray[child].heapPos = tempPos; 

            floatUp(heapArray, parent); 
          }
    }

    private static void sinkDown(int root, int bottom) {

        int leftChild, rightChild; // indexes of left and right children
        leftChild = root * 2 + 1;
        
        while (leftChild <= bottom) {
            rightChild = leftChild + 1;
            int smallerChild = leftChild;

            // check if right child exists and is smaller
            if (rightChild <= bottom && heapArray[rightChild].dValue < heapArray[leftChild].dValue) {
                smallerChild = rightChild;
            }

            // check if root is already smaller than its smallest child
            if (heapArray[root].dValue <= heapArray[smallerChild].dValue) {
                break;
            }

            swap(root, smallerChild);
            root = smallerChild;
            leftChild = root * 2 + 1;
        }
    }

        // when dValue(key) of the Airport is updated, the Airport could be moved up or down
    private static void decreaseKey(Airport heapArray[], int index) {
        int parentIndex = (index - 1) / 2;
        while (index > 0 && heapArray[index].dValue < heapArray[parentIndex].dValue) {

            Airport temp = heapArray[index];
            int tempPos = heapArray[index].heapPos;
            heapArray[index] = heapArray[parentIndex];
            heapArray[index].heapPos = heapArray[parentIndex].heapPos;
            heapArray[parentIndex] = temp;
            heapArray[parentIndex].heapPos = tempPos;
            
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    private static void decreaseKey2(Airport v, int new_dvalue) {
        v.dValue = new_dvalue;
        int a = v.heapPos;
        int b = (a-1)/3;
        while (v.dValue < heapArray[b].dValue && b>=0) {
            heapArray[a] = heapArray[b];
            heapArray[a].heapPos = a;
            heapArray[b] = v;
            v.heapPos = b;
            a = b;
            b = (a-1)/3;
        }
    }

    private static void swap(int first, int second) {
        Airport temp = heapArray[first];
        heapArray[first] = heapArray[second];
        heapArray[second] = temp;
    }

}

class Airport {
    String code;    // key for hash map which corresponds with a list of flights leaving from that airport
    int heapPos;
    int hashPos;
    public List<Flight> flightsList;
    int dValue;     // current earliest time we know so far to reach airport v
    String parentCode;  // airport code of airport where plane just came from
    Flight parentFlight;
    Airport parent;

    public Airport(String code) {
        this.code = code;
        this.heapPos = 0;
        this.hashPos = 0;
        this.flightsList = new LinkedList<>();
        this.dValue  = Integer.MAX_VALUE;
        this.parentFlight = null;
        this.parent = null;
    }

    public void addFlight(Flight flight) {
        flightsList.add(flight);
    }

    public List<Flight> getFlights() {
        return flightsList;
    }

}

class Flight {
    // String airline;
    // int flightNum;
    Airport origin;
    Airport dest;    // destination
    int departTime;   
    int arriveTime;     
    //int distance;
    Airport parentAirport;  
    Flight next;    //to be used in adjacency list       


    public Flight( Airport origin, Airport dest, int departTime, int arriveTime) {
        // this.airline = airline;
        // this.flightNum = flightNum;
        this.origin = origin;
        this.dest = dest;
        this.departTime = departTime;
        this.arriveTime = arriveTime;
        //this.distance = distance;
        this.parentAirport = null;
        this.next = null;
    }    
}
