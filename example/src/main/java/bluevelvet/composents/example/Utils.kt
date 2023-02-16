package bluevelvet.composents.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Helpers functions for the example app
 *
 * @author Morteza Taghdisi
 * @since 2023-02-15
 **/

fun createJob(completion: suspend () -> Unit) = CoroutineScope(Job() + Dispatchers.Main).launch { completion() }