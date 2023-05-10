import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Heap {
    private int a[]; 
    private int arraySize;
    private int heapSize; 
    
    public static void main(String[] args) throws FileNotFoundException {
        try{
            File file = new File("inputFile.txt");
            Scanner read = new Scanner(file); 
            
            int num = read.nextInt(); 
            Heap heap = new Heap(num); 
            
            for(int i =1; i <= num ; i++){
                String instruction = read.next();
                
                if(instruction.equals("IN")){
                    int element = read.nextInt();
                    heap.insert(element);
                }
                else if(instruction.equals("DK")){
                    int index = read.nextInt();
                    int newElement = read.nextInt(); 
                    heap.decreaceKey(index, newElement); 
            
                }
                else if(instruction.equals("EM")){
                    int min = heap.extractMin(); 
                    if( i == num){
                        System.out.print(min + "\n");
                    }
                }
            }
            
            
           read.close(); 
                  
        }
        
        catch(FileNotFoundException e){
                    System.out.println("Cant Find File");
                    e.printStackTrace(); 
                    }
        
    
    }
    
    public Heap(int aSize){
        arraySize = aSize;   
        heapSize = 0;
       a = new int[arraySize];
      
        
    }
    public void swap(int swapa, int swapb){
        int temp = a[swapa];
        a[swapa] = a[swapb]; 
        a[swapb] = temp; 
        
    }
    private int parent(int i){
        return(i-1) /3;
    }
    
    private int child(int i, int j){
        return 3*i+j; 
    }
    
    private void floatUp(int index){
        int parent = parent(index);
        
        if (index > 0 && a[index] < a[parent]){
            swap(index, parent);
            floatUp(parent); 
        }
    }
    
    private void sinkDown(int index){
        int smallest = index;
        
        for (int i=1; i <= 3; i++){
            int child = child(index, i);
            
            if(child < heapSize && a[child] < a[smallest]){
                smallest = child;
            }
        }
        if (smallest !=index){
            swap(index, smallest);
            sinkDown (smallest); 
        }
    }

    public void insert(int element){
        heapSize++; 
        if(heapSize == arraySize){
            System.out.println("Heap has reached max copacity");
           return; 
        }
        a[heapSize] = element; 
        floatUp(heapSize); 
        
    }
   
    
    public int extractMin(){
        if(heapSize == 0){
            
            System.out.println("Heap cannot be null or empty"); 
            return -1; 
        }
        int min = a[0]; 
            a[0] = a[heapSize -1]; 
            heapSize--; 
            sinkDown(0); 
            return min; 
    }
    
    public void decreaceKey(int index, int newElement){
        if(a[index] < newElement){
            floatUp(index); 
            System.out.println("New element is greater than current");
            return;
        } 
        
        a[index] = newElement; 
        floatUp(index); 
        
    }
    
} 
