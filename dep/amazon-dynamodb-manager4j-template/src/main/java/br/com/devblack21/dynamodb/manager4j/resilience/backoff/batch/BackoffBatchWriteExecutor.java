package br.com.devblack21.dynamodb.manager4j.resilience.backoff.batch;

import br.com.devblack21.dynamodb.manager4j.model.UnprocessedItem;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public interface BackoffBatchWriteExecutor<T> {

  void execute(final Function<List<T>, List<UnprocessedItem<T>>> batchFunction, final List<T> entities) throws ExecutionException, InterruptedException;

}
