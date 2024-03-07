/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Points to the first node in this list
    private Node first;

    // The number of elements in this list
    private int size;
	
    /** Constructs an empty list. */
    public List() {
        first = null;
        size = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
 	      return size;
    }

    /** Returns the first element in the list */
    public CharData getFirst() {
        return first.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData cD = new CharData(chr); // declare new CharData with given chr.
        Node newNode = new Node(cD, first); // create new node using Node() function.
        first = newNode;// update first.
        size++; // update size.
    }
    
    /** GIVE Textual representation of this list. */
    public String toString() {
        // check if there is anything return (base case).
        if (size == 0) {
            return "()";
        }
        String str = "(";
        Node current = first; // starting from the first node.
        while (current != null) {
            str += current.toString() + " "; // add the current value to the string.
            current = current.next; // iterate through the list.
        }
        // return the string without the trailig space and add ")" at the end.
        return str.substring(0, str.length() - 1) + ")";
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node current = first; // starting from the first node.
        int index = 0; // declare first index.
        while (current != null) {
            // check if chr in current cp is the same as given chr.
            if (current.cp.equals(chr)) {
                return index;
            }
            current = current.next; // iterate through the list.
            index++;
        }
        return -1; // there is no such object in this list.
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        int index = indexOf(chr);
        // check if the chr is in the list.
        if (index == -1) {
            addFirst(chr);
        }
        else {
            Node current = first; // starting from the first node.
            // go the the given chr in the list.
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.cp.count++; // update count.
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        Node perv = null;
        Node current = first;
        // go through the list until chr is found.
        while (current != null && current.cp.chr != chr) {
            perv = current;
            current = current.next;
        }
        // check if not found.
        if (current == null) {
            return false;
        }
        // check if first element.
        if (perv == null) {
            first = first.next;
        }
        else {
            perv.next = current.next; // "delete" the element containing chr.
        }
        size--; // update size.
        return true;
    }

    /** Returns the CharData object at the specified index in this list. 
     *  If the index is negative or is greater than the size of this list, 
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        // check base case according to the question's requirements.
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = first; // starting from the first node.
        // go the the given index in the list.
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
	    CharData[] arr = new CharData[size];
	    Node current = first;
	    int i = 0;
        while (current != null) {
    	    arr[i++]  = current.cp;
    	    current = current.next;
        }
        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
	    // If the list is empty, there is nothing to iterate   
	    if (size == 0) return null;
	    // Gets the element in position index of this list
	    Node current = first;
	    int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        // Returns an iterator that starts in that element
	    return new ListIterator(current);
    }
}
