package com.codelegger.androidmvvmbestskills.di

import javax.inject.Qualifier

/** Marks the IO-bound [kotlinx.coroutines.CoroutineDispatcher] for offloading blocking work. */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class IoDispatcher

/** Marks the default (CPU-bound) [kotlinx.coroutines.CoroutineDispatcher]. */
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultDispatcher
