package com.redpine.core.state

enum class LoadState {
    START,
    LOADING,
    SUCCESS,
    ERROR_NETWORK,
    NULL_RESPONSE,
    ERROR_AUTH,
    ENABLE_BUTTON
}