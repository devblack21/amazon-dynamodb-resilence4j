package br.com.devblack21.dynamodb.resilience.interceptor;

public interface RequestInterceptor<T> {

  void logSuccess(T entity);

  void logError(T entity, final Throwable throwable);

}