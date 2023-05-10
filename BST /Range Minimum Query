import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.Random; 




public class BST {
	
	static class Node{
		private int key;
		private int data;
		private int mindata;
		Node left, right, parent;
		
		public Node(int key, int data) {
			this.key = key;
			this.data = data ;
			this.mindata = data; 
			this.left = null;
			this.right = null;
			this.parent = null; 
		}
	}
	
	
	
	private static Node root = null; 
	    
	   
	
	public void insert(int key, int data) {
		Node newNode = new Node(key,data);
		newNode.left = null;
		newNode.right = null; 
		newNode.key = key;
		newNode.data = data;
		newNode.mindata = data;
		
		if (root == null) {
			root = newNode;
			newNode.parent = null; 
			return; 
		}
		else {
			Node currentNode = root; 
			while(currentNode != null) {
				if(currentNode.key < key) {
					if(currentNode.right != null) {
						currentNode = currentNode.right;
						
					}
					else {
						newNode.parent = currentNode; 
						currentNode.right = newNode; 
						break;
					}
				}
				else if(currentNode.key > key){
					if(currentNode.left != null) {
						currentNode = currentNode.left; 
					}
					else {
						newNode.parent = currentNode;
						currentNode.left = newNode; 
						break;
					}
				}
				else {
					if(currentNode.data > data) {
						currentNode.data = data; 
					}
					break;
				}
			}
		while(currentNode != null) {
			if(currentNode.mindata > data) {
				currentNode.mindata = data;
				currentNode = currentNode.parent; 
				
			}
			else break;
			
		}
		}
	}
	
	static int RMQ(Node x, int k1, int k2) {
		int leftMin; int rightMin;
		if(x != null) {
			if(x.key < k1) {
				return RMQ(x.right, k1,k2);
			
			}
			else if(x.key > k2) {
				return RMQ(x.left,k1,k2);
				
			}
			else {
				if(x.right == null && x.left == null){
					return x.data; 
				}
				else if(x.left == null) {
					if(x.data <= x.right.mindata)return x.data;
					return RMQ(x.right, k1, k2); 
				}
				else if(x.right == null) {
					if(x.data <= x.left.mindata)return x.data; 
					return RMQ(x.left, k1, k2);
				}
			
				else if(x.right.mindata > x.left.mindata) {
					leftMin = RMQ(x.left, k1, k2);
					rightMin = x.right.mindata; 
					if(rightMin < leftMin) 
						rightMin = RMQ(x.right,k1,k2);
						if (rightMin < leftMin) return rightMin; 
					return leftMin; 
					
				}
				else  {
					rightMin = RMQ(x.right, k1, k2); 
					leftMin = x.left.mindata; 
					if(leftMin <rightMin) 
						leftMin = RMQ(x.left, k1, k2);
						if(leftMin < rightMin) return leftMin;
					return rightMin; 
				}
		
				
			
			}
			
		}
		return Integer.MAX_VALUE;  
	}
    
    public static int Min ;
    
    public int RMQ1(Node root, int k1, int k2) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.key < k1) {
            return RMQ1(root.right, k1, k2);
        }
        if (root.key > k2) {
            return RMQ1(root.left, k1, k2);
        }
        return Math.min(root.data, Math.min(RMQ1(root.left,k1,k2), RMQ1(root.right,k1,k2)));
    }
    

    public static void printBST(Node root) {
        if (root == null) {
            return;
        }
        printBST(root.left);
        System.out.print(root.data + " ");
        printBST(root.right);
    }
    
    
    
    
    public static void main(String[] args) throws FileNotFoundException {
        try{
            File file = new File("inputFile.txt");
            Scanner read = new Scanner(file); 
            //node root=null;
            int num = read.nextInt(); 
            BST tree = new BST(); 
            
            for(int i =1; i <= num ; i++){
                String instruction = read.next();
                
                if(instruction.equals("IN")){

                    int key = read.nextInt();
                    int data = read.nextInt(); 
                    tree.insert(key, data);
                	
                }
                else if(instruction.equals("RMQ")){
                    int k1 = read.nextInt();
                    int k2 = read.nextInt(); 
                  System.out.println(RMQ(root,k1,k2)); 
                }
                
            }
            
          // printBST(root); 
            
            
           read.close(); 
                  
        }
        
        catch(FileNotFoundException e){
                    System.out.println("Cant Find File");
                    e.printStackTrace(); 
                    }
//        BST tree = new BST(); 
//    	long start = System.currentTimeMillis(); 
//    	for(int i=0; i<=32727; i++) {
//        	Random random = new Random(); 
//        	int d = random.nextInt(32767); 
//        	int k = random.nextInt(32767); 
//        	tree.insert(k, d);
//        	
//        }
//    	long end = (System.currentTimeMillis() - start); 
//    	System.out.println(end); 
//        
    
    }

    
    
	
	
	
	
	

}
