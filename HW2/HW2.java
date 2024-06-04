
class HW2
{
 
	// Node structure containing power and coefficient of variable
	static class Node
	{
	// Your code here
		public Node next;
		double coeff;
		int power;
		
		 public Node(double coeff, int power) {
			this.next=null;	
			this.coeff=coeff;
			this.power=power;
			}
	}
	//Function To Display The Linked list
	static void printList( Node ptr)
	{
		if (ptr == null) 
		{
			System.out.println();
			return;
		}
		else if (ptr.next != null)
		{
			while (ptr.next != null)
			{
				System.out.print( ptr.coeff + "x^" + ptr.power + " + ");
				ptr = ptr.next;
			}
		}
		System.out.println( ptr.coeff + "x^" + ptr.power);
	}
  
	// Create a node and return
	static Node createNode(double coeff, int power)
	{
	// Your code here
	     Node newNode= new Node(coeff,power);
	     
	     return newNode;
	}
  
  	// Function add a new node
	static Node addnode(Node head, double coeff, int power)
	{
	// Your code here
		Node current1=head;
		if(current1==null) {
			head=new Node(coeff,power);
			return head;
		}else {
			Node newNode= new Node(coeff,power);
			if(newNode.power>head.power) {
				newNode.next=head;
				newNode=head;
				return newNode;
				
			}else {
				while(current1.next!=null) {
					if(current1.power>newNode.power && current1.next.power<newNode.power) {
						newNode.next=current1.next;
						current1.next=newNode;
						return newNode;
					} current1=current1.next;
				} current1.next=newNode;
				return newNode;
			}
		}
		}
 
	static Node multiply(Node poly1, Node poly2)
	{
	// Your code here
		Node current=null;
		Node temp1=poly1;
		Node temp2=poly2;
		Node tempList=null;
		Node multi=new Node(0,0);
		while(temp1!=null) {
			while(temp2!=null) {
				multi.coeff=temp1.coeff*temp2.coeff;
				multi.power=temp1.power+temp2.power;
				if (tempList == null) {
					 tempList =createNode(multi.coeff, multi.power);
					 current = tempList;
					 } 
					 else {
					 current.next = createNode(multi.coeff, multi.power);
					 current = current.next;
					 }	
			 temp2=temp2.next;}
		temp2=poly2;
		temp1=temp1.next;}
		
		Node current2=tempList;
		Node index=null;
		double tCoeff;
		int tPower;
		if(tempList==null) {
			return simplify(tempList);
		}else {
			while(current2!=null) {
				index=current2.next;
				while(index!=null) {
					if(current2.power<index.power) {
						 tPower = current2.power; 
						 tCoeff=current2.coeff;
	                        current2.power = index.power; 
	                        current2.coeff=index.coeff;
	                        index.power = tPower;  
	                        index.coeff=tCoeff;
					} index=index.next;
				} current2=current2.next;
			}
		}
		return  simplify(tempList);
	}
	
  
	static Node add(Node poly1, Node poly2)
	{
	// Your code here
		Node addition=null;
		Node temp1=poly1;
		Node temp2=poly2;
		Node current=null;
		
		while(temp1!=null && temp2!=null) {
			
			if(temp1.power>temp2.power) {
				if (addition == null) {
					 addition =createNode(temp1.coeff, temp1.power);
					 current = addition;
					 } 
					 else {
					 current.next = createNode(temp1.coeff, temp1.power);
					 current = current.next;
					 }
				temp1=temp1.next;
			}else {
				if (addition == null) {
					 addition =createNode(temp2.coeff, temp2.power);
					 current = addition;
					 } 
				else {
					 current.next = createNode(temp2.coeff, temp2.power);
					 current = current.next;
					 }
				temp2=temp2.next;
			}
		}
		
		while(temp1!=null) {
			if (addition == null) {
				 addition =createNode(temp1.coeff, temp1.power);
				 current = addition;
				 } 
				 else {
				 current.next = createNode(temp1.coeff, temp1.power);
				 current = current.next;
				 }
			temp1=temp1.next;
		}
		
		while(temp2!=null) {
			if (addition == null) {
				 addition =createNode(temp2.coeff, temp2.power);
				 current = addition;
				 } 
				 else {
				 current.next = createNode(temp2.coeff, temp2.power);
				 current = current.next;
				 }
			temp2=temp2.next;
		}
		
		Node current2=addition;
		Node index=null;
		double tCoeff;
		int tPower;
		if(addition==null) {
			return simplify(addition);
		}else {
			while(current2!=null) {
				index=current2.next;
				while(index!=null) {
					if(current2.power<index.power) {
						 tPower = current2.power; 
						 tCoeff=current2.coeff;
	                        current2.power = index.power; 
	                        current2.coeff=index.coeff;
	                        index.power = tPower;  
	                        index.coeff=tCoeff;
					} index=index.next;
				} current2=current2.next;
			}
			
		}
		
		return simplify(addition);
	}

	private static Node simplify(Node addition) {
		
		Node current=null;
		Node result=null;
		Node temp=addition;
		Node temp2=temp.next;
	while(temp.next!=null) {
		
		if(temp.power==0 && temp2.power==0) {
			temp.coeff=temp.coeff+temp2.coeff;
			if (result == null) {
				 result =createNode(temp.coeff, temp.power);
				 current = result;
				 } 
				 else {
				 current.next = createNode(temp.coeff, temp.power);
				 current = current.next;
				 }
			temp.next=null;
		}else if(temp.power==temp2.power && temp2.next!=null) {
			temp.coeff=temp.coeff+temp2.coeff;
			
			temp2=temp2.next;
			
		}else if(temp.power==temp2.power && temp2.next==null){
			temp.coeff=temp.coeff+temp2.coeff;
			if (result == null) {
				 result =createNode(temp.coeff, temp.power);
				 current = result;
				 } 
				 else {
				 current.next = createNode(temp.coeff, temp.power);
				 current = current.next;
				 }
			temp.next=null;
		}
		else {
			if (result == null) {
				 result =createNode(temp.coeff, temp.power);
				 current = result;
				 } 
				 else {
				 current.next = createNode(temp.coeff, temp.power);
				 current = current.next;
				 }
			temp=temp2;
			temp2=temp2.next;
			
		}
	}
	if(temp!=null && temp2==null) {
		if (result == null) {
			 result =createNode(temp.coeff, temp.power);
			 current = result;
			 } 
			 else {
			 current.next = createNode(temp.coeff, temp.power);
			 current = current.next;
			 }
		
	}
	return result;
	
	}
}

