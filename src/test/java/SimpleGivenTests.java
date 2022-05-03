/* import static org.junit.jupiter.api.Assertions.*; */

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class SimpleGivenTests
{
    @Test
    public void oneStudent()
    {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        assertEquals(3.5, heap.extractMax().gpa(), .000001);
        assertEquals(0, heap.size());
    }

    @Test
    public void aInsertAFewStudents()
    {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Susan", 3.5, 60));
        heap.insert(new Student("Ben", 3.4, 70));
        heap.insert(new Student("Reed", 4.0, 120));
        heap.insert(new Student("Johnny", 1.2, 50));
        assertEquals(4.0, heap.extractMax().gpa(), .000001);
        assertEquals(3.5, heap.extractMax().gpa(), .000001);
        heap.insert(new Student("Billy", 2.7, 20));
        assertEquals(3.4, heap.extractMax().gpa(), .000001);
        assertEquals(2.7, heap.extractMax().gpa(), .000001);
        assertEquals(1.2, heap.extractMax().gpa(), .000001);
    }

    @Test
    public void exceptionTest()
    {
        MaxHeap heap = new MaxHeap(10);
        heap.insert(new Student("Ben", 3.4, 70));
        assertEquals(3.4, heap.extractMax().gpa(), .000001);
        try {
            heap.extractMax();
            fail("You shouldn't reach this line, an IndexOutOfBoundsException should have been thrown.");
        } catch (IndexOutOfBoundsException except) {
            assertEquals(except.getMessage(), "No maximum value:  the heap is empty.");
        }

    }

    @Test
    public void changeKeyTest()
    {
        MaxHeap heap = new MaxHeap(10);
        Student susan = new Student("Susan", 3, 6);
        Student ben = new Student("Ben", 2.4, 10);
        Student reed = new Student("Reed", 3.3, 3);
        Student johnny = new Student("Johnny", 1, 4);
        heap.insert(susan);;
        heap.insert(ben);
        heap.insert(johnny);
        heap.insert(reed);
        assertEquals(reed, heap.getMax());
        heap.addGrade(susan, 4, 3);  //should give her a 3.333333333 gpa
        assertEquals(susan, heap.getMax());
        assertEquals(3.33333333, heap.extractMax().gpa(), .000001);
        heap.addGrade(reed, .7, 3);  //should give him a 2.0
        heap.addGrade(johnny,  4,  4);  //should give him a 2.5
        assertEquals(2.5, heap.extractMax().gpa(), .000001);
        assertEquals(2.4, heap.extractMax().gpa(), .000001);
        assertEquals(2.0, heap.extractMax().gpa(), .000001);
    }

    @Test
    public void findStudentTest(){
        MaxHeap heap = new MaxHeap(10);
        Student susan = new Student("Susan", 3, 6);
        Student ben = new Student("Ben", 2.4, 10);
        Student reed = new Student("Reed", 3.3, 3);
        Student johnny = new Student("Johnny", 1, 4);
        heap.insert(susan);;
        heap.insert(ben);
        heap.insert(johnny);
        heap.insert(reed);
        assertEquals(3, heap.getStudentIndex(ben), 3);
        heap.addGrade(susan, 4,3); //should give her 3.3333 gpa and move her to the heapIndex 0
        assertEquals(0, heap.getStudentIndex(susan),0);
        assertEquals(1, heap.getStudentIndex(reed), 1);
    }

    @Test
    public void longEfficiencyTest(){
        MaxHeap heap = new MaxHeap(50);

        Student susan = new Student("Susan", 3, 6);
        Student ben = new Student("Ben", 2.4, 10);
        Student reed = new Student("Reed", 3.3, 3);
        Student johnny = new Student("Johnny", 1, 4);

        Student liz = new Student("Liz", 3.54, 6);
        Student stefan = new Student("Stefan", 2.1, 10);
        Student joe = new Student("Joe", 1.5, 3);
        Student eric = new Student("Eric", 3.9, 4);

        Student joel = new Student("Joel", 3.12, 6);
        Student lori = new Student("Lori", 2.46, 10);
        Student jenna = new Student("Jenna", 3.38, 3);
        Student michelle = new Student("Michelle", 2.69, 4);

        Student david = new Student("David", 3.65, 6);
        Student kristie = new Student("Kristie", 2.49, 10);
        Student sean = new Student("Sean", 3.32, 3);
        Student stephanie = new Student("Stephanie", 1.2, 4);

        heap.insert(susan);
        heap.insert(ben);
        heap.insert(reed);
        heap.insert(johnny);

        heap.insert(liz);
        heap.insert(stefan);
        heap.insert(joe);
        heap.insert(eric);

        heap.insert(joel);
        heap.insert(lori);
        heap.insert(jenna);
        heap.insert(michelle);

        heap.insert(david);
        heap.insert(kristie);
        heap.insert(sean);
        heap.insert(stephanie);

        assertEquals(eric, heap.getMax());

        heap.addGrade(michelle,7,4);

        assertEquals(michelle, heap.getMax());

    }
    @Test

    public void testCollectionConstructor(){

        Collection students = new ArrayList();
        Student susan = new Student("Susan", 3, 6);
        Student ben = new Student("Ben", 2.4, 10);
        Student reed = new Student("Reed", 3.3, 3);
        Student johnny = new Student("Johnny", 1, 4);

        students.add(susan);
        students.add(ben);
        students.add(reed);
        students.add(johnny);


        MaxHeap heap = new MaxHeap(students);

        assertEquals(reed, heap.getMax());
        assertEquals(2, susan.getIndex(),2);


    }



}