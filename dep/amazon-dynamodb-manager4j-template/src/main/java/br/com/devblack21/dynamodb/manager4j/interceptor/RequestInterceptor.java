package br.com.devblack21.dynamodb.manager4j.interceptor;

public interface RequestInterceptor<T> {

  void logSuccess(T entity);

  void logError(T entity, final Throwable throwable);

}