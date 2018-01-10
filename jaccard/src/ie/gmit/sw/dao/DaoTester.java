package ie.gmit.sw.dao;

import ie.gmit.sw.common.File2ListFetcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DaoTester {

    public static void main(String[] args) {

        Repository repo = DB4OContainer.getInstance();

        repo.getAllDocuments().forEach(System.out::println);
    }


}
