package ru.yalymar.proverka;

public class Paint {

	static int[] i = new int[]{-1, 6, 0, 9, -5, 6, -7, 80, 89 ,-1};

    public static void main(String[] args) {
    	delete(6);
        for(int i1: i){
            System.out.print(i1+" ");
        }
    }

	public static void delete(int number) {
		int shift = 0;
		for (int it : i) {
			if (it == number) {
				break;
			}
			shift++;
		}
		System.arraycopy(i, shift + 1, i, shift, i.length - shift - 1);
	}
}