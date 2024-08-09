package de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen;

import static de.supercode.dAY30_2_8_2024.algorithmenDatenstrukturen.BubbleSort.swap;

public class QuicksortPivotElement {
    public static void main(String[] args) {

    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);  //对基准元素左侧的子数组进行递归排序。
            quickSort(array, pivotIndex + 1, high); //对基准元素右侧的子数组进行递归排序。
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
       /* i 是一个索引，用于跟踪小于或等于基准元素的子数组的最后一个元素的位置。
       初始化 i 为 low - 1 表示在开始时，没有任何元素在较小子数组中。*/
/*
        这部分代码的作用是在快速排序算法中的 partition 方法中，将数组元素重新排列，
        以便将所有小于或等于基准（pivot）元素的值移动到基准元素的左侧，大于基准元素的值移动到基准元素的右侧。*/
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);  // Platziere den Pivot an der richtigen Position
        return i + 1;  //  den Pivot index bewegt sich nach recht 1
    }
}

/*
这段代码是快速排序算法的核心部分，负责递归地对数组的不同部分进行排序。它的作用是在每次调用 partition 方法后，通过递归对基准元素两边的子数组进行进一步排序。以下是对这段代码的详细解释：

代码解释
java
Code kopieren
if (low < high) {
    int pivotIndex = partition(array, low, high);
    quickSort(array, low, pivotIndex - 1);
    quickSort(array, pivotIndex + 1, high);
}
1. if (low < high)
作用:
这个条件检查当前子数组是否有两个以上的元素（即 low 索引小于 high 索引）。如果 low 不小于 high，这意味着子数组的大小为 0 或 1，不需要排序。
解释:
如果 low >= high，则当前子数组没有足够的元素进行排序（即已经排序好或为空），所以不需要进一步处理。
2. int pivotIndex = partition(array, low, high);
作用:
调用 partition 方法，选择一个基准元素，并将数组分区，使得所有小于等于基准元素的元素位于基准元素的左侧，所有大于基准元素的元素位于右侧。
解释:
partition 方法会返回基准元素的最终位置 pivotIndex，在这个位置上基准元素被放置在它应该在排序后的数组中的正确位置。
3. quickSort(array, low, pivotIndex - 1);
作用:
对基准元素左侧的子数组进行递归排序。
解释:
在 partition 过程中，基准元素 pivotIndex 已经被放置在正确的位置。左侧的子数组（从 low 到 pivotIndex - 1）还需要排序，所以递归调用 quickSort 来对这个子数组进行排序。
4. quickSort(array, pivotIndex + 1, high);
作用:
对基准元素右侧的子数组进行递归排序。
解释:
同样地，基准元素右侧的子数组（从 pivotIndex + 1 到 high）也需要排序，所以递归调用 quickSort 来对这个子数组进行排序。
总结
递归分区:

代码通过 partition 方法将数组分为两个部分，并且将基准元素放在正确的位置上。随后，左侧和右侧的子数组将分别递归排序。
递归终止条件:

if (low < high) 作为递归的终止条件。它确保递归只在子数组中有足够的元素时进行，而不会对已经排序或为空的子数组进行处理。
效果:

这种方法通过不断地将问题规模缩小，直到整个数组被排序完成。每次分区操作都会将基准元素放置在正确的位置，然后递归处理基准元素左右的子数组，直到所有子数组都被排序。
这段代码展示了快速排序的核心机制，即通过选择基准元素分区，并递归地对分区后的子数组进行排序，从而实现高效的排序过程。*/
