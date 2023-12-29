/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';

export class WxMpControllerService {

    /**
     * check
     * @param echostr echostr
     * @param nonce nonce
     * @param signature signature
     * @param timestamp timestamp
     * @returns string OK
     * @throws ApiError
     */
    public static checkUsingGet(
echostr?: string,
nonce?: string,
signature?: string,
timestamp?: string,
): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/',
            query: {
                'echostr': echostr,
                'nonce': nonce,
                'signature': signature,
                'timestamp': timestamp,
            },
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * receiveMessage
     * @returns any OK
     * @throws ApiError
     */
    public static receiveMessageUsingPost(): CancelablePromise<any> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/api/',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

    /**
     * setMenu
     * @returns string OK
     * @throws ApiError
     */
    public static setMenuUsingGet(): CancelablePromise<string> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/api/setMenu',
            errors: {
                401: `Unauthorized`,
                403: `Forbidden`,
                404: `Not Found`,
            },
        });
    }

}
