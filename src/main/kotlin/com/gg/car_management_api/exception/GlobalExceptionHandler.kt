package com.gg.car_management_api.exception

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

@Component
class GlobalExceptionHandler : DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError {
        return when (ex) {
            is CarNotFoundException -> GraphqlErrorBuilder.newError()
                .message(ex.message)
                .errorType(ErrorType.NOT_FOUND)
                .path(env.executionStepInfo.path)
                .build()

            is CarSaveException -> GraphqlErrorBuilder.newError()
                .message(ex.message)
                .errorType(ErrorType.INTERNAL_ERROR)
                .path(env.executionStepInfo.path)
                .build()

            else -> GraphqlErrorBuilder.newError()
                .message("An unexpected error occurred: ${ex.message}")
                .errorType(ErrorType.INTERNAL_ERROR)
                .path(env.executionStepInfo.path)
                .build()
        }
    }
}

class CarNotFoundException(message: String) : RuntimeException(message)

class CarSaveException(message: String) : RuntimeException(message)
