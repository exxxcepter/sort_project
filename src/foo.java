import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class foo {
    public static void main(String args[]){
        int[] A = {5, 2, 4, 6, 1, 3, 2, 6};
        System.out.println("A: " + Arrays.toString(A));
        new foo().Sort(A, 1, A.length);
        System.out.println("sorted A: " + Arrays.toString(A));
    }

    // разбивка массива на равные части
    public void Sort(int[] A, int p, int r){
        if (p < r){
            // определение середины массива
            int q = (new BigDecimal((p + r) / 2).setScale(3, RoundingMode.HALF_DOWN)).intValueExact();
            Sort(A, p, q);
            Sort(A, q + 1, r);
            Merge (A, p, q, r);
        }
    }

    // выполнение сортировки и слияние массивов
    public void Merge(int[] A, int p, int q, int r){
        int[] tmpA = new int[r];
        int leftIndex = p - 1, rightIndex = q, tmpIndex = 0;
        // пока не достигнут конец левой и правой половины:
        while ((leftIndex < q) && (rightIndex < r)){
            if (A[rightIndex] < A[leftIndex]){
                tmpA[tmpIndex] = A[rightIndex];
                rightIndex++;
                tmpIndex++;
            }
            else{
                tmpA[tmpIndex] = A[leftIndex];
                leftIndex++;
                tmpIndex++;
            }
        }
        // если левая половина закончена, дописать из правой
        if (leftIndex >= q){
            while (rightIndex < r){
                tmpA[tmpIndex] = A[rightIndex];
                rightIndex++;
                tmpIndex++;
            }
        }
        // если правая половина закончена, дописать из левой
        if (rightIndex >= r){
            while (leftIndex < q){
                tmpA[tmpIndex] = A[leftIndex];
                leftIndex++;
                tmpIndex++;
            }
        }
        // перезапись отсортированных данных из временного массива в исходный
        for (int i = p - 1, j = 0; i < r; i++, j++){
            A[i] = tmpA[j];
        }
    }
}
