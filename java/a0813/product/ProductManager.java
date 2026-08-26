package a0813.product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ArrayList로 상품을 관리하고,
 * File 클래스로 txt 파일에 읽고 쓰는 Manager
 */
public class ProductManager {
    private ArrayList<Product> products;
    private String filename;

    public ProductManager() {
        this.products = new ArrayList<>();
        // 데이터 파일 경로 (같은 폴더의 products.txt)
        this.filename = "d:/sande/teaching_plan/java/a0813/product/products.txt";
        ensureDirectory();
        loadFromFile();

        // 파일이 비어 있으면 더미 데이터 3개 저장
        if (products.isEmpty()) {
            addDummyData();
        }
    }

    // 폴더가 없으면 생성
    private void ensureDirectory() {
        File file = new File(filename);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
            System.out.println("디렉토리가 생성되었습니다: " + parentDir.getPath());
        }
    }

    // 더미 데이터 3개
    private void addDummyData() {
        products.add(new Product("P001", "노트북", 1200000, 5));
        products.add(new Product("P002", "무선마우스", 25000, 30));
        products.add(new Product("P003", "키보드", 45000, 20));
        saveToFile();
        System.out.println("더미 데이터 3개가 등록되었습니다.");
    }

    // 파일에서 읽기 (File + FileReader + BufferedReader)
    public void loadFromFile() {
        try {
            File file = new File(filename);

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("새 파일을 생성했습니다: " + filename);
                return;
            }

            products.clear();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    Product product = Product.fromFileString(line);
                    if (product != null) {
                        products.add(product);
                    }
                }
            }
            reader.close();
            System.out.println("파일에서 " + products.size() + "개 상품을 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        }
    }

    // 파일에 저장 (FileWriter + BufferedWriter)
    public void saveToFile() {
        try {
            File file = new File(filename);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Product product : products) {
                writer.write(product.toFileString());
                writer.newLine();
            }
            writer.close();
            System.out.println("파일에 저장되었습니다: " + filename);
        } catch (IOException e) {
            System.out.println("파일 저장 오류: " + e.getMessage());
        }
    }

    // Create - 등록
    public void addProduct(String id, String name, int price, int stock) {
        if (findProduct(id) != null) {
            System.out.println("이미 존재하는 상품 코드입니다.");
            return;
        }
        products.add(new Product(id, name, price, stock));
        saveToFile();
        System.out.println("상품이 등록되었습니다.");
    }

    // Read - 단건 조회
    public Product findProduct(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    // Read - 전체 조회
    public void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("등록된 상품이 없습니다.");
            return;
        }
        System.out.println("----- 전체 상품 목록 -----");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    // Update - 수정
    public boolean updateProduct(String id, String name, int price, int stock) {
        Product product = findProduct(id);
        if (product == null) {
            return false;
        }
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        saveToFile();
        return true;
    }

    // Delete - 삭제
    public boolean deleteProduct(String id) {
        Product product = findProduct(id);
        if (product == null) {
            return false;
        }
        products.remove(product);
        saveToFile();
        return true;
    }
}
