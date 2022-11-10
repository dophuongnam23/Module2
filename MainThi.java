package ThiModule2;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;


public class MainThi implements Comparator<SinhViens>,Serializable {
    static ArrayList<SinhViens> arr = new ArrayList<>();


    public static void ghiFie() {
        try {
            File path = new File("src/ThiModule2/data");
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arr);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void docFile() {
        File path = new File("src/ThiModule2/data");
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arr = (ArrayList<SinhViens>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            System.out.println("Không có cơ sở dữ liệu");
        }
    }

    public static void themSinhVien() {
        Pattern p = Pattern.compile("[a-z\\sA-Z]+");
        Pattern s = Pattern.compile("[0-9]+");
        Scanner sc = new Scanner(System.in);
        System.out.println("Mời nhập thông tin sinh viên: ");
        String id;
        while (true) {
            System.out.println("Nhập mã sinh viên (bắt buộc):");
            id = sc.nextLine();
            int count = 0;
            if (id.matches(String.valueOf(s)) && !id.equals(" ")) {
                for (int i = 0; i < arr.size(); i++) {
                    if (id.equals(arr.get(i).getId())) {
                        count++;
                        break;
                    }
                }
                if (count == 0) {
                    break;
                } else {
                    System.out.println("Mã sinh viên dã tồn tại. Vui lòng nhập lại");
                }

            } else {
                System.out.println("Mã sinh viên bắt buộc là số , không được để trống và (4-6) kí tự  !");
            }

        }
        String hoVaTen;
        while (true) {
            System.out.println("Nhập họ tên sinh viên (bắt buộc):");
            hoVaTen = sc.nextLine();
            if (hoVaTen.matches(String.valueOf(p)) && hoVaTen != " ") {
                break;
            } else {
                System.out.println("Họ và tên bắt buộc là chữ và không được để trống !");
            }
        }
        String tuoi;
        while (true) {
            System.out.println("Nhập tuổi");
            tuoi = sc.nextLine();
            if (tuoi.matches(String.valueOf(s)) && !tuoi.equals(" ")) {
                break;
            } else {
                System.out.println("Tuổi bắt buộc là số và không được để trống !");
            }
        }
        String gioiTinh;
        while (true) {
            System.out.println("Nhập giới tính ");
            gioiTinh = sc.nextLine();
            if (gioiTinh.matches(String.valueOf(p)) && gioiTinh != " ") {
                break;
            } else {
                System.out.println("Giới tính là chữ và không được để trống !");
            }
        }
        String diaChi;
        while (true) {
            System.out.println("Nhập địa chỉ");
            diaChi = sc.nextLine();
            if (diaChi.matches(String.valueOf(p)) && diaChi != " ") {
                break;
            } else {
                System.out.println("Địa chỉ bắt buộc là chữ và không được để trống !");
            }
        }
        System.out.println("Nhập điểm trung bình");
        double diemTrungBinh = sc.nextDouble();
        SinhViens sv = new SinhViens(id, hoVaTen, tuoi, gioiTinh, diaChi, diemTrungBinh);
        arr.add(sv);

    }

    public static int checkExit(String id) {
        for (SinhViens p : arr
        ) {
            if (p.getId().equals(id)) {
                return arr.indexOf(p);
            }
        }
        return -1;
    }

    public static void suaSinhVien() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Nhập mã sinh viên muốn sửa: ");
                String ids = scanner.nextLine();
                int index = 0;
                if (ids != "") {
                    if ((index = checkExit(ids)) != -1) {
                        for (int i = 0; i < arr.size(); i++) {
                            if (ids.equals(arr.get(i).getId())) {
                                System.out.println(arr.get(i).toString());
                                index = i;
                            }
                        }
                        System.out.println("Nhập tên mới: ");
                        String hoVaTens = scanner.nextLine();
                        if (!hoVaTens.equals("")) {
                            arr.get(index).setHoVaTen(hoVaTens);
                        }
                        System.out.println("Nhập tuổi mới: ");
                        String tuois = scanner.nextLine();
                        if (!tuois.equals("")) {
                            arr.get(index).setTuoi(tuois);
                        }
                        System.out.println("Nhập giới tính mới: ");
                        String gioiTinhs = scanner.nextLine();
                        if (!gioiTinhs.equals("")) {
                            arr.get(index).setGioiTinh(gioiTinhs);
                        }
                        System.out.println("Nhập địa chỉ mới: ");
                        String diaChis = scanner.nextLine();
                        if (!diaChis.equals("")) {
                            arr.get(index).setDiaChi(diaChis);
                        }
                        System.out.println("Nhập điểm trung bình  mới: ");
                        double diemTrungBinhs = scanner.nextDouble();
                        arr.get(index).setDiemTrungBinh(diemTrungBinhs);
                        break;

                    } else {
                        System.err.println("Không tìm được sinh viên với mã sinh viên trên !");
                    }


                } else {
                    main(new String[0]);
                }
            } catch (Exception e) {
                System.out.println("Vui lòng nhập lại");
            }
        }

    }


    public static void hienThi() {

        try {
            if (arr.isEmpty()) {
                System.out.println("Hiện không có sinh viên nào !");
            } else {
                for (SinhViens a : arr) {
                    System.out.println(a.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void xoaSinhVien() {
        while (true){
        Scanner scanner = new Scanner(System.in);
        for (SinhViens a : arr) {
            System.out.println(a.toString());
        }
        try {
            System.out.println("Nhập mã của sinh viên muốn xóa: ");
            String ids = scanner.nextLine();
            Pattern p1 = Pattern.compile("(?:Y|N)");
            int index;
            if (ids != "") {
                if ((index = checkExit(ids)) != -1) {
                    String answer;
                    while (true) {
                        System.out.println("Bạn có chắc chắc muốn xóa ?");
                        System.out.println("ấn Y để đồng ý, N để từ chối !");
                        answer = scanner.nextLine();
                        if (answer.matches(String.valueOf(p1))) {
                            if (answer.equals("Y")) {
                                arr.remove(index);
                                break;
                            } else if (answer.equals("N")) {
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("không tìm được sinh viên ứng với mã sinh viên trên !");
                }
            } else {
                main(new String[0]);
            }
        }catch (NumberFormatException e) {
            System.err.println("Thông tin bạn nhập chưa phù hợp! Mời nhập lại!");
        }
    }
    }

    public static void xapSepDTBTangDan() {
        try {
            arr.sort(Comparator.comparing(SinhViens::getDiemTrungBinh));
            for (SinhViens p : arr
            ) {
                System.out.println(p);

            }
        } catch (Exception e) {
            System.out.println("Tất cả sinh viên phải có điểm ! ");
        }
    }

    public static void xapSepGiamDan() {
        try {
            for (int i = 0; i < arr.size(); i++) {
                double max = arr.get(i).getDiemTrungBinh();
                for (int j = i; j < arr.size(); j++) {
                    if (max < arr.get(j).getDiemTrungBinh()) {
                        SinhViens temp = arr.get(i);
                        arr.set(i, arr.get(j));
                        arr.set(j, temp);
                        max = arr.get(i).getDiemTrungBinh();
                    }
                }
                System.out.println(arr.get(i).toString());
            }

        } catch (Exception e) {
            System.out.println("Tất cả sinh viên phải có điểm !");
        }
    }



    public static void main(String[] args) {
        int luaChon = -1;
        Scanner sc = new Scanner(System.in);
        while (true) {

            System.out.println("---- CHƯƠNG TRÌNH QUẢN LÝ SINH VIÊN ---- ");
            System.out.println(" Chọn chức năng theo sô ( để tiếp tục) ");
            System.out.println("1. Xem danh sách sinh viên:");
            System.out.println("2.  Thêm mới sinh viên");
            System.out.println("3.  Cập nhật");
            System.out.println("4.  Xóa");
            System.out.println("5.  Sắp xếp :");
            System.out.println("6.  Đọc từ file: ");
            System.out.println("7.  Ghi vào file");
            System.out.println("8.  Thoát");
            try {
                luaChon = Integer.parseInt(sc.next());
            } catch (Exception e) {
                System.out.println("Không được nhập chữ !");
                sc.nextLine();
            }
            switch (luaChon) {
                case 1:
                    hienThi();
                    break;
                case 2:
                    themSinhVien();
                    break;
                case 3:
                    suaSinhVien();
                    break;
                case 4:
                    xoaSinhVien();
                    break;
                case 5:
                    xapSep();
                    break;
                case 6:
                    docFile();
                    hienThi();
                    break;
                case 7:
                    ghiFie();
                    break;
                case 8:
                    System.exit(8);
                default:
                    System.out.println("Vui lòng nhập đúng số !");
            }
        }

    }

    public static void xapSep() {
        Scanner sc = new Scanner(System.in);
        int luaChon = -1;
        do {
            System.out.println("---- Sắp xếp sinh viên theo điểm trung bình ---- ");
            System.out.println("Chọn chức năng theo số ( để tiếp tục) ");
            System.out.println("1. Sắp xếp điểm trung bình tăng dần");
            System.out.println("2. Sắp xếp điểm trung bình giảm dần");
            System.out.println("3. Thoát");
            System.out.println(" Chọn chức năng: ");
            try {
                luaChon = Integer.parseInt(sc.next());

            } catch (Exception e) {
                System.out.println("Không được nhập chữ!");
            }
            switch (luaChon) {
                case 1:
                    xapSepDTBTangDan();
                    break;
                case 2:
                    xapSepGiamDan();
                    break;
                default:
                    System.out.println("Vui lòng nhập đúng số !");
            }
        } while (luaChon != 3);
    }


    @Override
    public int compare(SinhViens o1, SinhViens o2) {
        return 0;
    }
}
