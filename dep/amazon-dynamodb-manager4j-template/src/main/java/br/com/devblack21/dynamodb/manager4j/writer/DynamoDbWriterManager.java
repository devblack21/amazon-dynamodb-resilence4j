package br.com.devblack21.dynamodb.manager4j.writer;

public interface DynamoDbWriterManager<T> {

  void save(T entity);

  void batchSave(T entity);

  void delete(T entity);

  void batchDelete(T entity);

}