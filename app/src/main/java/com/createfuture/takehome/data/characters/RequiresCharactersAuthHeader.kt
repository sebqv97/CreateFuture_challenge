package com.createfuture.takehome.data.characters

/**
 * the sole purpose of using Annotation classes such as the below one is to have a one-place (Interceptor)
 * where headers can be populated to requests.
 * The below one will allow requests needing that authorisation header having it attached prior to Retrofit sending it to backend
 * */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class RequiresCharactersAuthHeader
