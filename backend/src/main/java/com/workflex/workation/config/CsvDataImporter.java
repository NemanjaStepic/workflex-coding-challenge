package com.workflex.workation.config;
import com.workflex.workation.model.Workation;
import com.workflex.workation.repository.WorkationRepository;
import org.apache.commons.csv.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
@Component public class CsvDataImporter implements ApplicationRunner {
 private final WorkationRepository repository;
 public CsvDataImporter(WorkationRepository repository){this.repository=repository;}
 @Override public void run(ApplicationArguments args) throws IOException {
  List<Workation> records=new ArrayList<>();
  try(Reader reader=new InputStreamReader(new ClassPathResource("workations.csv").getInputStream(),StandardCharsets.UTF_8);
      CSVParser parser=CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).setIgnoreSurroundingSpaces(true).get().parse(reader)){
   for(CSVRecord row:parser){
    String risk=row.get("risk").trim().toUpperCase(Locale.ROOT);
    if(!Set.of("HIGH","LOW","NO","HIGH_RISK","LOW_RISK","NO_RISK").contains(risk)) throw new IllegalArgumentException("Invalid risk on CSV row "+row.getRecordNumber());
    records.add(new Workation(row.get("workationId"),row.get("employee"),row.get("origin"),row.get("destination"),
       LocalDate.parse(row.get("start")),LocalDate.parse(row.get("end")),Integer.parseInt(row.get("workingDays")),risk));
   }
  }
  repository.saveAll(records); // idempotent by CSV workationId; no manual database entry
 }
}
