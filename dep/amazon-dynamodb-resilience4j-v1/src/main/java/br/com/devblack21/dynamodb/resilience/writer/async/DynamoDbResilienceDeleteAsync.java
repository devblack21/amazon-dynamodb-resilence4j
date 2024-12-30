package br.com.devblack21.dynamodb.resilience.writer.async;

import br.com.devblack21.dynamodb.resilience.backoff.BackoffExecutor;
import br.com.devblack21.dynamodb.resilience.backoff.ErrorRecoverer;
import br.com.devblack21.dynamodb.resilience.interceptors.RequestInterceptor;
import br.com.devblack21.dynamodb.resilience.writer.DynamoDbResilienceDelete;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.concurrent.ExecutorService;


public class DynamoDbResilienceDeleteAsync<T> extends AbstractAsyncWriter<T> implements DynamoDbResilienceDelete<T> {

  private final DynamoDBMapper dynamoDBMapper;
  private final RequestInterceptor<T> requestInterceptor;

  public DynamoDbResilienceDeleteAsync(final DynamoDBMapper dynamoDBMapper,
                                       final BackoffExecutor backoffExecutor,
                                       final ErrorRecoverer<T> errorRecoverer,
                                       final ExecutorService executorService,
                                       final RequestInterceptor<T> requestInterceptor) {
    super(backoffExecutor, errorRecoverer, executorService, requestInterceptor);
    this.dynamoDBMapper = dynamoDBMapper;
    this.requestInterceptor = requestInterceptor;
  }

  @Override
  public void delete(final T entity) {
    this.execute(entity);
  }

  @Override
  public void executor(final T entity) {
    this.dynamoDBMapper.delete(entity);
    this.logSuccess(entity);
  }

  private void logSuccess(final T entity) {
    if (this.requestInterceptor != null) {
      this.requestInterceptor.logSuccess(entity);
    }
  }

}
