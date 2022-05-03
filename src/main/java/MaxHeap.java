import java.util.ArrayList;
import java.util.Collection;

public class MaxHeap
{
    private ArrayList<Student> students;

    public MaxHeap(int capacity)
    {
        students = new ArrayList<Student>(capacity);
    }

    public MaxHeap(Collection<Student> collection)
    {
        students = new ArrayList<Student>(collection);

        for(int i = 0; i < size(); i++){ // set indices
            students.get(i).setIndex(i);
        }

        for(int i = size()/2 - 1; i >= 0; i--)
        {
            maxHeapify(i);
        }
    }

    public Student getMax()
    {
        if(size() < 1)
        {
            throw new IndexOutOfBoundsException("No maximum value:  the heap is empty.");
        }
        return students.get(0);
    }

    public Student extractMax()
    {
        Student value = getMax();
        students.set(0,students.get(size()-1));
        students.get(0).setIndex(0);
        students.remove(size()-1);
        maxHeapify(0);
        return value;
    }

    public int size()
    {
        return students.size();
    }

    public void insert(Student elt)
    {
        students.add(elt);
        int index = size() - 1;
        elt.setIndex(index);
        moveUp(index);

    }

    public void addGrade(Student elt, double gradePointsPerUnit, int units)
    {
        int index = getStudentIndex(elt); // ask for the students index
        elt.addGrade(gradePointsPerUnit, units); // change grade
        moveUp(index); // move student, which should update index
        maxHeapify(index); //
    }


    private int parent(int index)
    {
        return (index - 1)/2;
    }

    private int left(int index)
    {
        return 2 * index + 1;
    }

    private int right(int index)
    {
        return 2 * index + 2;
    }

    private void swap(int from, int to)
    {
        Student val = students.get(from);
        swapIndex(from, to);
        students.set(from,  students.get(to));
        students.set(to,  val);

    }

    private void maxHeapify(int index)
    {
        int left = left(index);
        int right = right(index);
        int largest = index;
        if (left <  size() && students.get(left).compareTo(students.get(largest)) > 0)
        {
            largest = left;
        }
        if (right <  size() && students.get(right).compareTo(students.get(largest)) > 0)
        {
            largest = right;
        }
        if (largest != index)
        {
            swap(index, largest);
            maxHeapify(largest);

        }
    }

    private void moveUp(int index)
    {
        while(students.get(index).compareTo(students.get(parent(index))) > 0){ // Starts at the end of the heap, and compares to parent. Iterates through heap to compare to parents.
            swap(index, parent(index));
            index = parent(index); // Index starts at the end of ArrayList, then iterates from the back. Loop continues while the child is greater than parent
        }
    }

    private void swapIndex(int from, int to){

        int parent = students.get(to).getIndex();
        int child = students.get(from).getIndex();

        students.get(from).setIndex(parent);
        students.get(to).setIndex(child);
    }

    public int getStudentIndex(Student elt){
        return elt.getIndex();
    }
}


