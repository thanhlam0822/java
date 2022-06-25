package Selection;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
public class Test {

    // Hàm sắp xếp nổi bọt
    void bubbleSort(int[] arr) {
        long start = System.nanoTime();
        int n = arr.length;
        for (int i = 0; i < n -1 ; i++)
            for (int j =  0; j < n - i - 1 ; j++)
                if (arr[j] > arr[j+1]) {
                    // swap arr[j+1] và arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        long end = System.nanoTime();
                long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
    }
    void selectionSort(int arr[]) {
        long start = System.nanoTime();
        int n = arr.length;
        // Duyệt qua từng phần tử của mảng
        for (int i = 0; i < n - 1; i++) {

            // Tìm phần tử nhỏ nhất trong mảng chưa được sắp xếp
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Hoán đổi phần tử nhỏ nhất và phần tử đầu tiên
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        long end = System.nanoTime();
        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
    }
    // Thuật toán Merge
    // Merge hai mảng con của arr[].
    // Mảng con thứ nhất là arr[l..m]
    // Mảng con thứ hai là arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {

        // Tìm kích thước của 2 mảng con để merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Tạo mảng tạm
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy dữ liệu vào mảng tạm
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge các mảng tạm lại

        // Chỉ mục ban đầu của 2 mảng con
        int i = 0, j = 0;

        // Chỉ mục ban đầu của mảng phụ được hợp nhất
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Sao chép các phần tử còn lại của L[] nếu có
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Sao chép các phần tử còn lại của R[] nếu có
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void mergeSort(int arr[], int l, int r) {
        long start = System.nanoTime();
        if (l < r) {

            // Tìm điểm chính giữa
            int m = (l + r) / 2;

            // Hàm đệ quy tiếp tục chia đôi
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
    }

    // Thuật toán vun đống
    public void heapSort(int arr[]) {
        long start = System.nanoTime();
        int n = arr.length;

        // Xây dựng Heap (sắp xếp lại mảng)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Trích xuất từng phần tử từ Heap
        for (int i = n - 1; i > 0; i--) {
            // Di chuyển root hiện tại đến end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // gọi max - heapify trên Heap đã sắp xếp
            heapify(arr, i, 0);
        }
        long end = System.nanoTime();

        // execution time
        long execution = end - start;
        System.out.println("Execution time: " + execution + " nanoseconds");
    }

    // Để vun đống một cây con bắt nguồn từ nút i là
    // một chỉ mục trong arr[]. n là kích thước của Heap
    void heapify(int arr[], int n, int i) {
        int largest = i; // Khởi tạo largest như root
        int l = 2 * i; // left = 2*i
        int r = 2 * i + 1; // right = 2*i + 1

        // Nếu nút con bên trái lớn hơn nút con của gốc
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // Nếu nút con bên phải lớn hơn largest
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // Nếu largest không phải là root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Vun đống lại các cây con bị ảnh hưởng
            heapify(arr, n, largest);
        }
    }
    // Thuật toán sắp xếp nhanh
    // Hàm nhận phần tử cuối cùng làm chốt,
    // đặt các phần tử nhỏ hơn chốt ở trước
    // và lớn hơn ở sau nó
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {

            // Nếu phần tử hiện tại nhỏ hơn chốt
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] và arr[high] (hoặc pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // arr[] --> Mảng cần được sắp xếp,
    // low --> chỉ mục bắt đầu,
    // high --> chỉ mục kết thúc
    void quickSort(int arr[], int low, int high) {
     //   long start = System.nanoTime();
        if (low < high) {

            // pi là chỉ mục của chốt, arr[pi] vị trí của chốt
            int pi = partition(arr, low, high);

            // Sắp xếp đệ quy các phần tử
            // trướcphân vùng và sau phân vùng
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
     //   long end = System.nanoTime();

        // execution time
     //   long execution = end - start;
   //     System.out.println("Execution time: " + execution + " nanoseconds");
    }

    // In các phần tử của arr
    void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        Test ob = new Test();
        int choose, n ;
        System.out.println("Nhập kích thước mảng");
        n = sc.nextInt();
        int arr[] = new int[n];
        // Tự động mở Notepad
        ProcessBuilder pb = new ProcessBuilder("Notepad.exe", "D:\\newfile.txt");
        ProcessBuilder pb2 = new ProcessBuilder("Notepad.exe", "D:\\newfile2.txt");
        do {
            menuInput();
            System.out.println("Chọn cách nhập Input");

            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Nhập mảng từ bàn phím");
                    for (int i =0; i < n; i++) {
                        arr[i] = sc.nextInt();
                    }
                    System.out.println("Mảng ban đầu là");
                    ob.printArray(arr);
                    do {
                        menuSort();
                        System.out.println("Chọn thuật toán");
                        choose = sc.nextInt();
                    switch(choose) {
                        case 4:
                            ob.bubbleSort(arr);
                            break;
                        case 5:
                            ob.selectionSort(arr);
                            break;
                        case 6:

                            ob.mergeSort(arr, 0, arr.length - 1);

                            break;
                        case 7:
                            ob.heapSort(arr);
                            break;
                        case 8:
                            ob.quickSort(arr, 0, n - 1);
                            break;

                    }
                        do {
                            menuOutput();
                            System.out.println("Chọn cách Output");
                            choose = sc.nextInt();
                            switch (choose) {
                                case 9 :
                                    System.out.println("Mảng đã sắp xếp hiển thị ở console");
                                    ob.printArray(arr);
                                    break;
                                case 10 :
                                    // Xuất mảng đã nhập từ bàn phím ra file txt
                                    System.out.println("Mảng đã xuất ra file!!!");
                                    File file1 = new File("D:\\newfile2.txt");
                                    OutputStream outputStream = new FileOutputStream(file1);
                                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

                                    for (Integer item: arr){
                                        outputStreamWriter.write(item.toString());
                                        // Dùng để xuống hàng
                                        outputStreamWriter.write("\n");
                                    }
                                    // Đây là phương thức quan trọng!
                                    // Nó sẽ bắt chương trình chờ ghi dữ liệu xong thì mới kết thúc chương trình.
                                    outputStreamWriter.flush();
                                    pb2.start();
                                    break;
                            }
                        } while(choose != 11);
                    } while (choose != 11 );



                    break;
                case 2:
                    System.out.println("Nhập mảng bằng sinh ngẫu nhiên");
                    Random random = new Random();
                    for(int i=0; i<n; i++) {
                        arr[i]=random.nextInt(n);
                    }
                    System.out.println("Mảng ban đầu là");
                    ob.printArray(arr);

                    do {
                        menuSort();
                        System.out.println("Chọn thuật toán");
                        choose = sc.nextInt();
                        switch(choose) {
                            case 4:
                                ob.bubbleSort(arr);
                                break;
                            case 5:
                                ob.selectionSort(arr);
                                break;
                            case 6:
                                ob.mergeSort(arr, 0, arr.length - 1);
                                break;
                            case 7:
                                ob.heapSort(arr);
                                break;
                            case 8:
                                ob.quickSort(arr, 0, n - 1);
                                break;

                        }
                        do {
                            menuOutput();
                            System.out.println("Chọn cách Output");
                            choose = sc.nextInt();
                            switch (choose) {
                                case 9 :
                                    System.out.println("Mảng đã sắp xếp hiển thị ở console");
                                    ob.printArray(arr);
                                    break;
                                case 10 :
                                    System.out.println("Mảng đã xuất ra file!!!");
                                    File file1 = new File("D:\\newfile2.txt");
                                    OutputStream outputStream = new FileOutputStream(file1);
                                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

                                    for (Integer item: arr){
                                        outputStreamWriter.write(item.toString());
                                        // Dùng để xuống hàng
                                        outputStreamWriter.write("\n");
                                    }
                                    // Đây là phương thức quan trọng!
                                    // Nó sẽ bắt chương trình chờ ghi dữ liệu xong thì mới kết thúc chương trình.
                                    outputStreamWriter.flush();
                                    pb2.start();
                                    break;
                            }
                        } while(choose != 11);
                    } while (choose != 11 );

                    break;

                case 3:

                    System.out.println("Nhập mảng từ file văn bản");
                    Scanner scanner = new Scanner(new File("D:\\newfile.txt"));

                    int i = 0;
                    pb.start();


                        do {

                        menuSort();
                        System.out.println("Chọn thuật toán");

                        choose = sc.nextInt();


                        switch(choose) {
                            case 4:

                                while(scanner.hasNextInt()){
                                    arr[i++] = scanner.nextInt();
                                }

                                ob.bubbleSort(arr);

                                break;
                            case 5:

                                while(scanner.hasNextInt()){
                                    arr[i++] = scanner.nextInt();
                                }

                                ob.selectionSort(arr);
                                break;
                            case 6:

                                while(scanner.hasNextInt()){
                                    arr[i++] = scanner.nextInt();
                                }

                                ob.mergeSort(arr, 0, arr.length - 1);
                                break;
                            case 7:

                                while(scanner.hasNextInt()){
                                    arr[i++] = scanner.nextInt();
                                }

                                ob.heapSort(arr);
                                break;
                            case 8:

                                while(scanner.hasNextInt()){
                                    arr[i++] = scanner.nextInt();
                                }

                                ob.quickSort(arr, 0, n - 1);
                                break;
                        }
                        do {
                            menuOutput();
                            System.out.println("Chọn cách Output");
                            choose = sc.nextInt();
                            switch (choose) {

                                case 9 :
                                    //  xuất mảng ra console
                                  ob.printArray(arr);


                                    break;
                                case 10 :
                                    //  xuất ra file txt

                                    FileOutputStream fo = new FileOutputStream("D:\\newfile2.txt");
                                    PrintWriter out = new PrintWriter(fo);
                                    for ( i=0; i<arr.length; i++)
                                        out.printf("%-5d",arr[i]);
                                    out.close();

                                  pb2.start();
                                    break;
                            }
                        } while(choose != 11);
                    } while (choose != 11 );

                    break;


                    case 11:
                    System.out.println("Thoat");
                    break;

                default:
                    System.out.println("Chon lai choose = ");
                    break;
            }


        } while(choose != 11);

    }
// Tạo các menu
    static void menuInput() {
        System.out.println("1. Nhập mảng từ bàn phím ");
        System.out.println("2. Nhập mảng bằng cách sinh ngẫu nhiên ");
        System.out.println("3. Nhập mảng từ file văn bản ");

    }
    static void menuSort() {
        System.out.println("4. Thuật toán sắp xếp nổi bọt (Bubble Sort) ");
        System.out.println("5. Thuật toán sắp xếp chọn (Selection Sort");
        System.out.println("6. Thuật toán sắp xếp trộn (Merge Sort) ");
        System.out.println("7. Thuật toán sắp xếp vun đống (Heap Sort) ");
        System.out.println("8. Thuật toán sắp xếp nhanh (Quick Sort) ");
    }
    static void menuOutput() {
        System.out.println("9.  Xuất mảng ra console ");
        System.out.println("10. Xuất mảng ra file văn bản ");
        System.out.println("11. Thoát!!!");


    }

}
