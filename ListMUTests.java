import org.junit.*;
import org.junit.runner.RunWith;

import mu.grading.*;
import static mu.grading.Assert.*;

import java.util.Arrays;


// Runs tests on LinkedList.java.
// Defines lists of integers and performs operations to verify its correctness.

@Reporting(lowDetails = true, medDetails = true, highDetails = true)
@TestCategory(name = "size", points = 6.0)
@TestCategory(name = "isEmpty", points = 2.0)
@TestCategory(name = "clear", points = 3.0)
@TestCategory(name = "get", points = 10.0)
@TestCategory(name = "set", points = 10.0)
@TestCategory(name = "add", points = 10.0)
@TestCategory(name = "addAtIndex", points = 10.0)
@TestCategory(name = "remove", points = 10.0)
@TestCategory(name = "removeAtIndex", points = 10.0)
@TestCategory(name = "contains", points = 8.0)
@TestCategory(name = "indexOf", points = 8.0)
@TestCategory(name = "lastIndexOf", points = 8.0)
@TestCategory(name = "toArray", points = 5.0)
@RunWith(GradingRunner.class)
public class ListMUTests {
	// returns [ "0", "1", "2", .... "N-1"
	public static Integer[] all(int n) {
		Integer[] result = new Integer[n];
		for (int i = 0; i < n; i++) {
			result[i] = i;
		}
		return result;
	}
	// similar to method above, but skips one of the numbers
	public static Integer[] allBut(int n, int oneToOmit) {
		Integer[] result = new Integer[n - 1];
		int pos = 0;
		for (int i = 0; i < n; i++) {
			if (i != oneToOmit) {
				result[pos++] = i;
			}
		}
		return result;
	}
	// returns true if the list contains the exactly the elements in the array
	// items.
	public static boolean listContainsTheseElements(LinkedList274<Integer> list,
													Integer [] items) {
		Object[] storedItems = list.toArray();
		return Arrays.equals(items, storedItems) && list.size() == items.length;
	}
	// returns true iff the list contains the sequence 0, 1, ... N-1
	public static boolean listContains0toN_1(LinkedList274<Integer> list, int N) { 
		return listContainsTheseElements(list, all(N));
	}
	// returns true iff the list contains the sequence 0, 1, ... N-1, with one
	// exception
	public static boolean listContainsAllBut(LinkedList274<Integer> list, int N, int omit) { 
		return listContainsTheseElements(list, allBut(N, omit));
	}
	// creates list containing the following "0", "1", "2", .... "N-1"
	public static LinkedList274<Integer> createList0toN_1(int n) {
		return createList(all(n));
	}
	// creates list containing the items provided in the input array
	public static LinkedList274<Integer> createList(Integer[] items) {
		LinkedList274<Integer> list = new LinkedList274<Integer>();
		for (Integer i : items)
			list.add(i);
		return list;
	}
	@GradingTest(category = "size", percentage = 1.0)
	@Test(timeout = 500)
	public void sizeTest() {
		// create list of several sizes and check size()'s behavior
		for (int sz = 0; sz <= 5; sz++) {
			LinkedList274<Integer> L = createList0toN_1(sz);
			assertEquals(sz, L.size());
		}
	}
	@GradingTest(category = "isEmpty", percentage=1.0)
	@Test(timeout = 500)
	public void isEmptyTest() {
		LinkedList274<Integer> empty = new LinkedList274<Integer>();
		assertTrue(empty.isEmpty());
		assertTrue(listContains0toN_1(empty, 0));

		for (int sz = 1; sz <= 5; sz++) {
			LinkedList274<Integer> L = createList0toN_1(sz);
			assertFalse(L.isEmpty());
			assertTrue(listContains0toN_1(L, sz));
		}
	}
	@GradingTest(category = "clear", percentage = 1.0, gradingScheme = "allornothing")
	@Test(timeout = 500)
	public void clearTest() {
		// create list of several sizes and check clear's behavior
		for (int sz = 0; sz <= 5; sz++) {
			LinkedList274<Integer> L = createList0toN_1(sz);
			L.clear();
			assertTrue(L.isEmpty());
			assertTrue(listContains0toN_1(L, 0));
		}
	}
	@GradingTest(category = "get", percentage = 0.7, gradingScheme="allornothing")
	@Test(timeout = 500)
	public void getListsLength1to5() {
		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz-1; i++) {
				LinkedList274<Integer> L = createList0toN_1(sz);
				assertEquals((Integer)i, L.get(i));
				assertTrue(listContains0toN_1(L, sz));
			}
		}
	}
	@GradingTest(category = "get", percentage = 0.3, 
			gradingScheme="[0.0-0.3)=0.0,[0.3-0.5)=0.3,[0.5-1.0)=0.6,[1.0-1.0]=1.0")
	@Test(timeout = 500)
	public void getExceptions() {
		LinkedList274<Integer> L = new LinkedList274<Integer>();
		assertThrows(IndexOutOfBoundsException.class, () -> { L.get(-1); });
		assertThrows(IndexOutOfBoundsException.class, () -> { L.get(0); });

		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz; i++) {
				LinkedList274<Integer> L1 = createList0toN_1(sz);
				assertThrows(IndexOutOfBoundsException.class, () -> { L1.get(-1); });
				LinkedList274<Integer> L2 = createList0toN_1(sz);
				final int pos = sz;
				assertThrows(IndexOutOfBoundsException.class, () -> { L2.get(pos); });
				LinkedList274<Integer> L3 = createList0toN_1(sz);
				assertThrows(IndexOutOfBoundsException.class, () -> { L3.get(100); });
			}
		}
	}
	@GradingTest(category = "set", percentage = 0.7)
	@Test(timeout = 500)
	public void length1UpToLength5Lists() {
		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz; i++) {
				Integer[] originals = all(sz);
				Integer[] result = all(sz);
				result[i] = 999;
				LinkedList274<Integer> L = createList(originals);
				Integer x = L.set(i, 999);
				boolean OK = listContainsTheseElements(L, result);
				assertEquals((Integer)i, x);
				assertTrue(OK);
			}
		}
	}
	@GradingTest(category = "set", percentage = 0.3)
	@Test(timeout = 500)
	public void exceptionalConditions() {
		LinkedList274<Integer> L0 = new LinkedList274<Integer>();
		LinkedList274<Integer> L1 = createList0toN_1(1);
		LinkedList274<Integer> L4 = createList0toN_1(4);

		assertThrows(IndexOutOfBoundsException.class, () -> { L0.set(-1, 99); });
		assertThrows(IndexOutOfBoundsException.class, () -> { L0.set(0, 99); });
		assertThrows(IndexOutOfBoundsException.class, () -> { L1.set(1, 99); });
		assertThrows(IndexOutOfBoundsException.class, () -> { L4.set(-10, 99); });
		assertThrows(IndexOutOfBoundsException.class, () -> { L4.set(-1, 99); });
		assertThrows(IndexOutOfBoundsException.class, () -> { L4.set(5, 99); });
	}
	@GradingTest(category = "add", percentage = 1.0)
	@Test(timeout = 500)
	public void addTest() {
		LinkedList274<Integer> list = new LinkedList274<Integer>();
		for (int sz = 0; sz <= 5; sz++) {
			Integer stringToAdd = sz;
			list.add(stringToAdd);
			assertTrue(listContains0toN_1(list, sz + 1));
		}
	}
	@GradingTest(category = "addAtIndex", percentage = 0.5)
	@Test(timeout = 500)
	public void addAtIndexLength1orMorenTheInteriorTest() {
		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz - 1; i++) {
				Integer[] missingOneValue = allBut(sz, i); // 0, 1, 3, 4
				LinkedList274<Integer> L = createList(missingOneValue);
				Integer stringToAdd = i;
				L.add(i, stringToAdd);
				assertTrue(listContains0toN_1(L, sz));
			}
		}
	}
	@GradingTest(category = "addAtIndex", percentage = 0.2)
	@Test(timeout = 500)
	public void addAtIndexLength1orMoreAddAtEndTest() {
		for (int sz = 1; sz <= 5; sz++) {
			LinkedList274<Integer> L = createList0toN_1(sz);
			Integer stringToAdd = sz;
			L.add(sz, stringToAdd);
			assertTrue(listContains0toN_1(L, sz + 1));
		}
	}
	@GradingTest(category = "addAtIndex", percentage = 0.15)
	@Test(timeout = 500)
	public void addAtIndexEmptyListTest() {
		LinkedList274<Integer> L = new LinkedList274<Integer>();
		L.add(0, 0);
		assertTrue(listContainsTheseElements(L, new Integer[] { 0 }));
	}
	@GradingTest(category = "addAtIndex", percentage = 0.15, 
			gradingScheme = "[0.0-0.3)=0.0,[0.3-0.5)=0.5,[0.5-1.0)=0.75,[1.0-1.0]=1.0")
	@Test(timeout = 500)
	public void addAtIndexIllegalIndexAdd() {
		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz; i++) {
				LinkedList274<Integer> L1 = createList0toN_1(sz);
				assertThrows(IndexOutOfBoundsException.class, () -> {
					L1.remove(-1);
				});
				LinkedList274<Integer> L2 = createList0toN_1(sz);
				final int pos = sz;
				assertThrows(IndexOutOfBoundsException.class, () -> {
					L2.remove(pos);
				});
				LinkedList274<Integer> L3 = createList0toN_1(sz);
				assertThrows(IndexOutOfBoundsException.class, () -> {
					L3.remove(100);
				});
			}
		}
	}
	@GradingTest(category = "remove", percentage = 0.8)
	@Test(timeout = 500)
	public void withDuplicatesAndItemIsPresent() {
		LinkedList274<Integer> A = createList( new Integer [] {0,1,0,2});
		LinkedList274<Integer> B = createList( new Integer [] {0,1,0,2});
		LinkedList274<Integer> C = createList( new Integer [] {0,1,0,2});
		assertTrue(A.remove((Integer)0));
		assertTrue(listContainsTheseElements(A, new Integer [] {1,0,2}));
		assertTrue(B.remove((Integer)1));
		assertTrue(listContainsTheseElements(B, new Integer [] { 0,0,2}));
		assertTrue(C.remove((Integer)2));
		assertTrue(listContainsTheseElements(C, new Integer [] { 0,1,0}));
	}
	@GradingTest(category = "remove", percentage = 0.2)
	@Test(timeout = 500)
	public void removeWhereItemIsNotPresent() {
		for (int sz = 1; sz <= 5; sz++) {
			LinkedList274<Integer> L = createList0toN_1(sz);
			assertFalse(L.remove((Integer)999));
			assertTrue(listContains0toN_1(L, sz));
		}
	}
	@GradingTest(category = "removeAtIndex", percentage = 1.0)
	@Test(timeout = 500)
	public void length1orMoreRemoveInTheInterior() {
		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz - 1; i++) {
				LinkedList274<Integer> L = createList0toN_1(sz);
				assertEquals((Integer)i, L.remove(i));
				assertTrue(listContainsAllBut(L, sz, i));
			}
		}
	}
	@GradingTest(category = "contains", percentage = 0.6)
	@Test(timeout = 500)
	public void atLeastLength1ElementUniqueElements() {
		LinkedList274<Integer> L1 = createList0toN_1(1);
		LinkedList274<Integer> L2 = createList0toN_1(2);
		LinkedList274<Integer> L5 = createList0toN_1(5);

		for (int i = 0; i < 1; i++)
			assertTrue(L1.contains(i));
		assertFalse(L1.contains(99));
		assertTrue(listContains0toN_1(L1, 1));

		for (int i = 0; i < 2; i++)
			assertTrue(L2.contains(i));
		assertFalse(L2.contains(99));
		assertTrue(listContains0toN_1(L2, 2));

		for (int i = 0; i < 5; i++)
			assertTrue(L5.contains(i));
		assertFalse(L5.contains(99));
		assertTrue(listContains0toN_1(L5, 5));
	}
	@GradingTest(category = "contains", percentage = 0.2)
	@Test(timeout = 500)
	public void emptyListContains() {
		LinkedList274<Integer> L0 = new LinkedList274<Integer>();
		assertFalse(L0.contains(999));
		assertTrue(listContains0toN_1(L0, 0));
	}
	@GradingTest(category = "contains", percentage = 0.2)
	@Test(timeout = 500)
	public void listWithDuplicatesContains() {
		LinkedList274<Integer> L = createList(new Integer[] { 4,4 });
		assertTrue(L.contains(4));
		assertFalse(L.contains(99));
	}
	@GradingTest(category = "indexOf", percentage = 0.35)
	@Note(description="Creates lists of length 1, 2, and 5.\n" +
						"Cerforms multiple searches on each: " +
						"first element, second element, ... last element")
	@Test(timeout = 500)
	public void indexOfNoDuplicatesAndSuccessSearch() {
		LinkedList274<Integer> L1 = createList0toN_1(1);
		LinkedList274<Integer> L2 = createList0toN_1(2);
		LinkedList274<Integer> L5 = createList0toN_1(5);

		assertEquals(0, L1.indexOf(0));
		assertTrue(listContains0toN_1(L1, 1));

		assertEquals(0, L2.indexOf(0));
		assertEquals(1, L2.indexOf(1));
		assertTrue(listContains0toN_1(L2, 2));

		assertEquals(0, L5.indexOf(0));
		assertEquals(1, L5.indexOf(1));
		assertEquals(2, L5.indexOf(2));
		assertEquals(3, L5.indexOf(3));
		assertEquals(4, L5.indexOf(4));
		assertTrue(listContains0toN_1(L5, 5));
	}
	@GradingTest(category = "indexOf", percentage = 0.35)
	@Test(timeout = 500)
	public void indexOfWithDuplicatesAndSuccessfulSearch() {
		LinkedList274<Integer> L1 = createList(new Integer[] { 0,1,0 });
		LinkedList274<Integer> L2 = createList(new Integer[] {1, 0, 0, 0});
		LinkedList274<Integer> L3 = createList(new Integer[] { 0,1,0,1});

		assertEquals(0, L1.indexOf(0));
		assertEquals(1, L1.indexOf(1));

		assertEquals(1, L2.indexOf(0));
		assertEquals(0, L2.indexOf(1));

		assertEquals(0, L3.indexOf(0));
		assertEquals(1, L3.indexOf(1));
	}
	@GradingTest(category = "indexOf", percentage = 0.3)
	@Test(timeout = 500)
	public void indexOfUnsuccessfulSearch() {
		LinkedList274<Integer> L1 = createList(new Integer[] { 0 });
		LinkedList274<Integer> L2 = createList(new Integer[] { 0, 1 });
		LinkedList274<Integer> L3 = createList(new Integer[] { 0,1,2 });
		LinkedList274<Integer> L4 = createList(new Integer[] { 0,0,1,0});

		assertEquals(-1, L1.indexOf(1));

		assertEquals(-1, L2.indexOf(2));
		assertEquals(-1, L2.indexOf(3));

		assertEquals(-1, L3.indexOf(10));
		assertEquals(-1, L3.indexOf(10));
		assertEquals(-1, L3.indexOf(10));

		assertEquals(-1, L4.indexOf(10));
	}
	@GradingTest(category = "lastIndexOf", percentage = 0.35)
	@Test(timeout = 500)
	public void lastIndexOfNoDuplicatesAndSuccessSearch() {
		LinkedList274<Integer> L1 = createList0toN_1(1);
		LinkedList274<Integer> L2 = createList0toN_1(2);
		LinkedList274<Integer> L5 = createList0toN_1(5);

		assertEquals(0, L1.lastIndexOf(0));
		assertTrue(listContains0toN_1(L1, 1));

		assertEquals(0, L2.lastIndexOf(0));
		assertEquals(1, L2.lastIndexOf(1));
		assertTrue(listContains0toN_1(L2, 2));

		assertEquals(0, L5.lastIndexOf(0));
		assertEquals(1, L5.lastIndexOf(1));
		assertEquals(2, L5.lastIndexOf(2));
		assertEquals(3, L5.lastIndexOf(3));
		assertEquals(4, L5.lastIndexOf(4));
		assertEquals(5, L5.lastIndexOf(5));
		assertTrue(listContains0toN_1(L5, 5));
	}
	@GradingTest(category = "lastIndexOf", percentage = 0.35, gradingScheme="allornothing")
	@Test(timeout = 500)
	public void lastIndexOfWithDuplicatesAndSuccessSearch() {
		LinkedList274<Integer> L1 = createList(new Integer[] { 0,1,0});
		LinkedList274<Integer> L2 = createList(new Integer[] { 1, 0, 0, 0});
		LinkedList274<Integer> L3 = createList(new Integer[] { 0,1,0,1});

		assertEquals(2, L1.lastIndexOf(0));
		assertEquals(1, L1.lastIndexOf(1));

		assertEquals(3, L2.lastIndexOf(0));
		assertEquals(0, L2.lastIndexOf(1));

		assertEquals(2, L3.lastIndexOf(0));
		assertEquals(3, L3.lastIndexOf(1));
	}
	@GradingTest(category = "lastIndexOf", percentage = 0.3)
	@Test(timeout = 500)
	public void lastIndexOfUnsuccessfulSearch() {
		LinkedList274<Integer> L0 = createList(new Integer[] {});
		LinkedList274<Integer> L1 = createList(new Integer[] { 0 });
		LinkedList274<Integer> L2 = createList(new Integer[] { 0, 1 });
		LinkedList274<Integer> L3 = createList(new Integer[] { 0,1,2 });
		LinkedList274<Integer> L4 = createList(new Integer[] { 0,0,1,0});

		assertEquals(-1, L0.lastIndexOf(10));
		assertEquals(-1, L1.lastIndexOf(10));
		assertEquals(-1, L2.lastIndexOf(10));
		assertEquals(-1, L3.lastIndexOf(10));
		assertEquals(-1, L4.lastIndexOf(0));
	}
	@GradingTest(category = "toArray", percentage = 0.8)
	@Test(timeout = 500)
	public void toArrayNotEmptyList() {
		for (int sz = 1; sz <= 5; sz++) {
			for (int i = 0; i < sz; i++) {
				LinkedList274<Integer> L = createList0toN_1(sz);
				assertTrue(Arrays.equals(L.toArray(), all(sz)));
			}
		}
	}
	@GradingTest(category = "toArray", percentage = 0.2)
	@Test(timeout = 500)
	public void toArrayEmptyList() {
		LinkedList274<Integer> L0 = new LinkedList274<Integer>();
		assertTrue(Arrays.equals(L0.toArray(), new String[] {}));
	}
	public static void main(String[] args) {
		GradingRunner.run();
	}
}
