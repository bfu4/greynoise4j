/**
 * The client package contains objects structured for creating a functional
 * http client and a functional Greynoise client.
 *
 * Most importantly, the {@link com.localhost22.greynoise4j.client.GreynoiseClient}
 * has methods that return data types described in {@link com.localhost22.greynoise4j.structs}
 * that may be used functionally.
 *
 * This package also contains a functional interface that may be used
 * when making custom requests, when deciding how to handle the sending of that
 * request. The constraint to that interface is that the lambda <em>MUST</em>
 * return an {@link io.vertx.core.Future} of an {@link io.vertx.ext.web.client.HttpResponse} of
 * a {@link io.vertx.core.buffer.Buffer}, as seen described in
 * {@link com.localhost22.greynoise4j.client.RequestHandler#handle(io.vertx.ext.web.client.HttpRequest)}.
 */
package com.localhost22.greynoise4j.client;
