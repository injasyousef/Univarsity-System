package application;

public class HTable<T extends Comparable<T>> {
	HNode<T>[] table;
	int capacity = 11;
	int size = 0;

	public HTable(int capacity) {
		if (capacity >= 1)
			this.capacity = nextPrime(capacity);
		table = new HNode[this.capacity];
		
		 for (int i = 0; i < table.length; i++) {
	            table[i] = new HNode<T>(null, 'E');
	        }
	}
	
	
	public T search(T s) {
        if(s != null && size > 0) {
            int index = s.hashCode();
            int currIndex = index%capacity;
            int i = 0;

            while (table[currIndex].getF() != 'F' && table[currIndex].getData().compareTo(s) != 0 ) {
                currIndex = (int) (index + Math.pow(i, 2)) % capacity;
                i++;
            }
           // System.out.println("Found at index: " + currIndex);

            if(table[currIndex].getData() != null) {
                if(table[currIndex].getData().compareTo(s) == 0) {
                   return table[currIndex].getData();
                }
            }
        }
        return null;
    }

	
	public String print() {
		String s="";
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null)
				s=s+"null"+"\n";
			else
				s=s+table[i].toString()+"\n";
		}
		s=s+"The table size is: "+size;
		return s;
	}

	public void insert(T data) {
		if (capacity < 2 * size)
			rehash();
		size++;

		HNode<T> newNode = new HNode<>(data,'F');

		int hashCode = data.hashCode();
		int index = (hashCode) % capacity;
		int i = 0;
		int amir = index;
		do {
			if (table[amir] == null || table[amir].getF() != 'F') {
				table[amir] = newNode;
				break;
			}
			i++;
			amir = index + i * i;
			amir %= capacity;
		} while (true);
	}

	public T delete(T data) {
		
		if(size<capacity/4)
			shrink();
		
		int hashCode = data.hashCode();
		int index = hashCode % capacity;
		int i = 0;
		int amir = index;
		do {
			if (table[index] == null) {
				System.out.println("Not Found!!!");
				return null;
			}
			if (table[index].getData().compareTo(data) == 0) {
				table[index].setF('D');
				size--;
				return table[index].getData();
			}
			i++;
			amir = index + i * i;
			amir %= capacity;
		} while (true);

	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		capacity = nextPrime(capacity);
		this.capacity = capacity;
	}

	public int getSize() {
		return size;
	}

	public HNode<T>[] getTable() {
		return table;
	}

	private void rehash() {
		int newCapacity = nextPrime(capacity * 2);
		HNode[] temp = new HNode[newCapacity];
		for (int j = 0; j < capacity - 1; j++) {
			if (table[j] == null)
				continue;
			if (table[j].getF() == 'F') {

				int k = table[j].getData().hashCode() % temp.length, i = 1;
				while (true) {
					if (temp[k] == null) {
						temp[k] = new HNode(table[j].getData(),'F');
						break;
					} else if (temp[k].getF() == 'D') {
						temp[k] = new HNode(table[j].getData(),'F');
						break;
					}
					k += i * i;
					i++;
					k %= capacity;
				}
			}
		}
		table = temp;
		capacity = newCapacity;
	}
	
	
	
	public void shrink() {
		int newCapacity = nextPrime(capacity / 2);
		HNode[] temp = new HNode[newCapacity];
		for (int j = 0; j < capacity - 1; j++) {
			if (table[j] == null)
				continue;
			if (table[j].getF() == 'F') {

				int k = table[j].getData().hashCode() % temp.length, i = 1;
				while (true) {
					if (temp[k] == null) {
						temp[k] = new HNode(table[j].getData(),'F');
						break;
					} else if (temp[k].getF() == 'D') {
						temp[k] = new HNode(table[j].getData(),'F');
						break;
					}
					k += i * i;
					i++;
					k %= capacity;
				}
			}
		}
		table = temp;
		capacity = newCapacity;
    }

	

	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		for (; !isPrime(n); n += 2)
			;
		return n;
	}

	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;
		if (n == 1 || n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n / 2; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

}